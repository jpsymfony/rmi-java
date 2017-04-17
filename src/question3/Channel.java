package question3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

/**
 * The interface Channel.
 */
public interface Channel extends Remote, Serializable
{
    /**
     * Send message.
     *
     * @param message the message
     * @throws RemoteException the remote exception
     */
    public void sendMessage(Message message) throws RemoteException;
}
