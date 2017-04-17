package question2;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import java.rmi.registry.Registry;
import java.rmi.Naming;

import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.util.List;
import java.util.ArrayList;

/**
 * La classe Observable, devient un service rmi.
 */
public class Observable extends UnicastRemoteObject implements RemoteObservableIF
{
    private List<RemoteObserverIF> observers;

    /**
     * Constructeur.
     *
     * @param registry l'annuaire de noms
     * @throws RemoteException       the remote exception
     * @throws UnknownHostException  the unknown host exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     */
    public Observable(Registry registry)
            throws RemoteException,
            UnknownHostException,
            NotBoundException,
            MalformedURLException
    {
        observers = new ArrayList<RemoteObserverIF>();
        registry.rebind(RemoteObservableIF.OBSERVABLE_NAME, this);
    }

    /**
     * Constructeur par defaut
     * Utilisation ici de la classe Naming soit la configuration par defaut (rmiregistry, port 1099)
     *
     * @throws RemoteException       the remote exception
     * @throws UnknownHostException  the unknown host exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     */
    public Observable() throws RemoteException,
            UnknownHostException,
            NotBoundException,
            MalformedURLException
    {
        /* a completer */
        /* utilisation de la classe Naming */
        observers = new ArrayList<RemoteObserverIF>();
        Naming.rebind(RemoteObservableIF.OBSERVABLE_NAME, this);
    }

    /**
     * Ajout d'un observateur.
     *
     * @param observer l'observateur.
     */
    public void addObserver(RemoteObserverIF observer) throws RemoteException
    {
      /* a completer */
      observers.add(observer);
    }

    /**
     * Notification a tous les observateurs inscrits d'un observateur.
     *
     * @param arg la cause de cette notification.
     */
    public void notifyObservers(Serializable arg) throws RemoteException
    {
        for (RemoteObserverIF obs : observers) {
            obs.update(RemoteObservableIF.OBSERVABLE_NAME, arg);
        }
    }
} 