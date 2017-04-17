package question3;

import java.rmi.RemoteException;

/**
 * The interface Recipient list.
 */
public interface RecipientList extends Channel
{
    /**
     * Add route.
     *
     * @param source       the source
     * @param destinations the destinations
     * @throws RemoteException the remote exception
     */
    public void addRoute(InputChannel source, Channel[] destinations) throws RemoteException;
}
