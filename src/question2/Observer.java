package question2;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.Registry;

import java.rmi.server.UnicastRemoteObject;

/**
 * La classe Observable, devient un service rmi.
 */

public class Observer extends UnicastRemoteObject implements RemoteObserverIF
{
    public Observer(String name, RemoteObservableIF observable, Registry registry) throws RemoteException,
            MalformedURLException,
            UnknownHostException
    {
 
        /* a completer : 2 lignes */
        /** enregistrement aupres de l'annuaire registry, ( appel de rebind ) */
        /** inscription comme observateur, ( appel de addObserver) */
        registry.rebind(RemoteObservableIF.OBSERVABLE_NAME, this);
        observable.addObserver(this);
    }

    private Serializable observable;
    private Serializable arg;

    public synchronized void update(Serializable observable, Serializable arg) throws RemoteException
    {
        this.observable = observable;
        this.arg = arg;
    }

    /**
     * pour les tests, l'observable de la derniere notification recue, ne pas modifier
     */
    public synchronized Serializable getLastObservable() throws RemoteException
    {
        return observable;
    }

    /**
     * pour les tests, l'argument de la derniere notification recue, ne pas modifier
     */
    public synchronized Serializable getLastArg() throws RemoteException
    {
        return arg;
    }

}

