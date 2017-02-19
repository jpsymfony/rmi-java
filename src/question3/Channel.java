package question3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface Channel extends Remote, Serializable
{
    public void sendMessage(Message message) throws RemoteException;
}
