package question2;

import java.io.Serializable;
import java.rmi.*;

public interface RemoteObserverIF extends Remote
{
    public void update(Serializable observable, Serializable arg) throws RemoteException;

    /**
     * pour les tests, l'observable de la derniere notification recue
     */
    public Serializable getLastObservable() throws RemoteException;

    /**
     * pour les tests, l'argument de la derniere notification recue
     */
    public Serializable getLastArg() throws RemoteException;

}