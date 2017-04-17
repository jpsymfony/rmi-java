package question2;

import java.io.Serializable;
import java.rmi.*;

/**
 * L'interface de l'observable distant
 */
public interface RemoteObservableIF extends Remote
{
    /**
     * The constant OBSERVABLE_NAME.
     */
    public static final String OBSERVABLE_NAME = "observable";

    /**
     * Ajout d'un observateur.
     *
     * @param observer l'observateur.
     * @throws RemoteException the remote exception
     */
    public void addObserver(RemoteObserverIF observer) throws RemoteException;

    /**
     * notification a tout les observateurs inscrits.
     *
     * @param arg la cause de cette notification.
     * @throws RemoteException the remote exception
     */
    public void notifyObservers(Serializable arg) throws RemoteException;
} 