package question3;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;

import java.util.*;

import java.io.*;
import java.rmi.MarshalledObject;

import java.rmi.activation.*;

public class RecipientListImplActivatable extends UnicastRemoteObject implements RecipientList
{
    private Map<InputChannel, Channel[]> store;  // une proposition
    private String name;

    /**
     * Ajout de la persistance
     */
    private static List<Message> messages = new ArrayList<>();
    private ActivationID id;

    public RecipientListImplActivatable(String name) throws RemoteException
    {
        this.name = name;
        this.store = new HashMap<InputChannel, Channel[]>();
    }

    public RecipientListImplActivatable(Registry registry, String name) throws RemoteException
    {
        this(name);
        Remote stub = this;
        boolean extendsUnicastRemoteObject = UnicastRemoteObject.class.isInstance(this); // à la place de this instanceof UnicastRemoteObject
        if (!extendsUnicastRemoteObject) stub = UnicastRemoteObject.exportObject(this, 0);
        registry.rebind(name, stub);
    }

    /**
     * Ajout de la persistance
     */
   /* Constructeur Activatable */
    public RecipientListImplActivatable(ActivationID id, MarshalledObject m) throws RemoteException
    {
        this("persistant");
        this.id = id;
        if (m != null) {
            try {
                List<Message> restoredMessages = (List<Message>) m.get();
                if (!restoredMessages.isEmpty()) {
                    System.out.println(" liste restituee : " + m.get());
                    for (Message msg : restoredMessages) {
                        System.out.println("on renvoie le message " + msg.getMessage() + " a " + msg.getSource().toString());
                        this.sendMessage(msg);
                    }
                }
            } catch (Exception e) {
            }
            Activatable.exportObject(this, id, 0);
        }
    }

    public synchronized void sendMessage(Message message) throws RemoteException
    {
        if (!messages.contains(message)) {
            /** Ajout de la persistance */
            messages.add(message);

            try {
                OutputStream os = new FileOutputStream(new File("liste.ser"));
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(new MarshalledObject(messages));
                oos.flush();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // a completer
        new RouterWorkThread(message, store.get(message.getSource()));
    }

    public void addRoute(InputChannel source, Channel[] destinations) throws RemoteException
    {
        // a completer
        this.store.put(source, destinations);
    }

    public String getName()
    {
        return this.name;
    }

    public String toString()
    {
        String res = "RecipientList:";
        for (InputChannel key : store.keySet()) {
            res = res + "<" + key + " : [";
            for (Channel channel : store.get(key)) {
                res = res + channel + " ";
            }
            res = res + "]";
        }
        res = res + ">";
        return res;
    }

    private class RouterWorkThread implements Runnable
    {
        // a completer
        private Thread runner;
        private Message message;
        private Channel[] destinations;

        /**
         * Instantiates a new Router work thread.
         *
         * @param message      the message
         * @param destinations the destinations
         */
        public RouterWorkThread(Message message, Channel[] destinations)
        {
            this.message = message;
            this.destinations = destinations;
            this.runner = new Thread(this);
            this.runner.start();
        }

        public void run()
        {
            // a completer: envoi du message aux destinataires agrees
            for (Channel receiver : this.destinations) {
                try {
                    receiver.sendMessage(this.message);
                } catch (RemoteException re) {
                    re.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        try {
            System.setSecurityManager(new RMISecurityManager());
            Properties props = new Properties();
            props.put("java.security.policy", "http://localhost:8086/java.policy");

            ActivationGroupDesc group = new ActivationGroupDesc(props, null);
            ActivationGroupID agi = ActivationGroup.getSystem().registerGroup(group);

            MarshalledObject m = null;
            try {
                InputStream is = new FileInputStream(new File("liste.ser"));
                ObjectInputStream oin = new ObjectInputStream(is);
                m = (MarshalledObject) oin.readObject();
                oin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ActivationDesc desc = new ActivationDesc(agi, "question3.RecipientListImplActivatable", "http://localhost:8086/question3/", m);

            Remote serveur = Activatable.register(desc);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("persistant", serveur);

            System.out.println("Le serveur persistant lointain vient de s'enregistrer");
        } catch (Exception e) {
            throw e;
        }
    }
}
