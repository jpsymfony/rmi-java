package question3;

import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;

public class UneExecutionModeRMI
{
    public static void main(String[] args) throws Exception
    {
        Registry registry = null;
        RecipientList recipient = null;
        Receiver r1 = null, r2 = null, r3 = null, r4 = null;
        try {
            try {
                registry = LocateRegistry.createRegistry(1250);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            try {
                registry = LocateRegistry.getRegistry(1250);
                recipient = new RecipientListImpl(registry, "test2");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

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
            r1_stub = r2_stub = r3_stub = r4_stub = null;
            try {
                recipient_stub = (RecipientList) registry.lookup("test2");
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

        } catch (Exception e) {
            //fail("exception inattendue : " + e.getMessage());   // e.printStackTrace();
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