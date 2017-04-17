package question2;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.Registry;

import java.rmi.server.UnicastRemoteObject;

/**
 * La classe Observer devient un service rmi.
 */
public class Observer extends UnicastRemoteObject implements RemoteObserverIF
{
    /**
     * Instantiates a new Observer.
     *
     * @param name       the name
     * @param observable the observable
     * @param registry   the registry
     * @throws RemoteException       the remote exception
     * @throws MalformedURLException the malformed url exception
     * @throws UnknownHostException  the unknown host exception
     */
    public Observer(String name, RemoteObservableIF observable, Registry registry) throws RemoteException,
            MalformedURLException,
            UnknownHostException
    {
        /* a completer : 2 lignes */
        /** enregistrement aupres de l'annuaire registry, ( appel de rebind ) */
        registry.rebind(RemoteObservableIF.OBSERVABLE_NAME, this);
        /** inscription comme observateur, ( appel de addObserver) */
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
