package question3;

import java.rmi.RemoteException;

public interface RecipientList extends Channel
{
    public void addRoute(InputChannel source, Channel[] destinations) throws RemoteException;
}
