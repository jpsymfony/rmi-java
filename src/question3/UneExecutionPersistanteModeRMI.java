package question3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.rmi.MarshalledObject;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.activation.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

/**
 * The type Une execution mode rmi.
 */
public class UneExecutionPersistanteModeRMI
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception
    {
        Registry registry = null;
        RecipientList recipient = null;
        Receiver r1 = null, r2 = null, r3 = null, r4 = null;

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

            registry = LocateRegistry.getRegistry();
            registry.rebind("persistant", serveur);

            System.out.println("Le serveur persistant lointain vient de s'enregistrer");

            try {
                r1 = new Receiver(registry, "A");
                r2 = new Receiver(registry, "B");
                r3 = new Receiver(registry, "C");
                r4 = new Receiver(registry, "D");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            InputChannel in = new InputString("in");
            RecipientList recipient_stub = null;
            Channel r1_stub, r2_stub, r3_stub, r4_stub;
            try {
                recipient_stub = (RecipientList) registry.lookup("persistant");
                r1_stub = (Channel) registry.lookup("A");
                r2_stub = (Channel) registry.lookup("B");
                r3_stub = (Channel) registry.lookup("C");
                r4_stub = (Channel) registry.lookup("D");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            try {
                recipient_stub.addRoute(in, new Channel[]{r1_stub, r2_stub, r3_stub, r4_stub});
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            Message message = new Message(in, "test1_message");
            recipient_stub.sendMessage(message);

            Thread.sleep(1000); // store_forward ...

            Message msg = r1.getReceived();
            System.out.println(" Message recu par A : " + msg.getMessage());

            msg = r2.getReceived();
            System.out.println(" Message recu par B : " + msg.getMessage());

            msg = r3.getReceived();
            System.out.println(" Message recu par C : " + msg.getMessage());

            msg = r4.getReceived();
            System.out.println(" Message recu par D : " + msg.getMessage());

            RecipientList recipient_stub2 = null;
            try {
                recipient_stub2.addRoute(in, new Channel[]{r1_stub, r2_stub, r3_stub, r4_stub});
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            Thread.sleep(1000); // store_forward ...

            msg = r1.getReceived();
            System.out.println(" Message recu par A : " + msg.getMessage());

            msg = r2.getReceived();
            System.out.println(" Message recu par B : " + msg.getMessage());
        } catch (Exception e) {
            throw e;
        } finally {
            for (String s : registry.list()) {
                try {
                    registry.unbind(s);
                } catch (Exception e) {
                }
            }
            try {
                UnicastRemoteObject.unexportObject(recipient, true);
            } catch (Exception e) {
            }
            try {
                UnicastRemoteObject.unexportObject(r1, true);
            } catch (Exception e) {
            }
            try {
                UnicastRemoteObject.unexportObject(r2, true);
            } catch (Exception e) {
            }
            try {
                UnicastRemoteObject.unexportObject(r3, true);
            } catch (Exception e) {
            }
            try {
                UnicastRemoteObject.unexportObject(r4, true);
            } catch (Exception e) {
            }

            try {
                java.rmi.server.UnicastRemoteObject.unexportObject(registry, true);
            } catch (Exception e) {
            }
        }
        System.exit(0); // radical !
    }

}