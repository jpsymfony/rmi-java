package question3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;

/**
 * The type Receiver.
 */
public class Receiver extends UnicastRemoteObject implements Channel
{
    private String name;
    private Message received;

    /**
     * Instantiates a new Receiver.
     *
     * @param name the name
     * @throws RemoteException the remote exception
     */
    public Receiver(String name) throws RemoteException
    {
        this.name = name;
    }

    /**
     * Instantiates a new Receiver.
     *
     * @param registry the registry
     * @param name     the name
     * @throws RemoteException the remote exception
     */
    public Receiver(Registry registry, String name) throws RemoteException
    {
        this(name);
        Remote stub = this;
        boolean extendsUnicastRemoteObject = UnicastRemoteObject.class.isInstance(this); // a la place de this instanceof UnicastRemoteObject
        if (!extendsUnicastRemoteObject) stub = UnicastRemoteObject.exportObject(this, 0);
        registry.rebind(name, stub);
    }

    public void sendMessage(Message message) throws RemoteException
    {
        System.out.println("message.getSource() : " + message.getSource());
        System.out.println("message.getMessage() : " + message.getMessage());
        this.received = message;
    }

    public void resetReceiver()
    {
        this.received = null;
    }

    /**
     * Gets received.
     *
     * @return the received
     */
    public Message getReceived()
    {
        return received;
    }

    public String toString()
    {
        return "Receiver : " + name;
    }
}
