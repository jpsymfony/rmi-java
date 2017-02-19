package question3;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;

import java.util.Map;
import java.util.HashMap;

import java.util.Set;

public class RecipientListImpl extends UnicastRemoteObject implements RecipientList
{
    private Map<InputChannel, Channel[]> store;  // une proposition
    private String name;

    public RecipientListImpl(String name) throws RemoteException
    {
        this.name = name;
        this.store = new HashMap<InputChannel, Channel[]>();
    }

    public RecipientListImpl(Registry registry, String name) throws RemoteException
    {
        this(name);
        Remote stub = this;
        boolean extendsUnicastRemoteObject = UnicastRemoteObject.class.isInstance(this); // a la place de this instanceof UnicastRemoteObject
        if (!extendsUnicastRemoteObject) stub = UnicastRemoteObject.exportObject(this, 0);
        registry.rebind(name, stub);
    }

    public synchronized void sendMessage(Message message) throws RemoteException
    {
        // a completer
        new RouterWorkThread(message, store.get(message.getSource()));
    }

    public void addRoute(InputChannel source, Channel[] destinations) throws RemoteException
    {
        // a completer
        store.put(source, destinations);
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

}
