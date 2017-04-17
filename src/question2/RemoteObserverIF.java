package question2;

import java.io.Serializable;
import java.rmi.*;

/**
 * The interface Remote observer if.
 */
public interface RemoteObserverIF extends Remote
{
    /**
     * Update.
     *
     * @param observable the observable
     * @param arg        the arg
     * @throws RemoteException the remote exception
     */
    public void update(Serializable observable, Serializable arg) throws RemoteException;

    /**
     * pour les tests, l'observable de la derniere notification recue
     *
     * @return the last observable
     * @throws RemoteException the remote exception
     */
    public Serializable getLastObservable() throws RemoteException;

    /**
     * pour les tests, l'argument de la derniere notification recue
     *
     * @return the last arg
     * @throws RemoteException the remote exception
     */
    public Serializable getLastArg() throws RemoteException;

}