package question2;

import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.io.Serializable;

/**
 * The type Main observers.
 */
public class MainObservers
{
    /**
     * The entry point of application.
     *
     * @param arg the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] arg) throws Exception
    {
        System.setSecurityManager(new RMISecurityManager());
        RemoteObservableIF observable = (RemoteObservableIF) Naming.lookup("observable");
        Observer obs1 = new SimpleObserver("obs1", observable, LocateRegistry.getRegistry());
        Observer obs2 = new SimpleObserver("obs2", observable, LocateRegistry.getRegistry());

        Thread.sleep(1000);

        observable.notifyObservers("notification !");
        observable.notifyObservers("notification2 !");
    }

    private static class SimpleObserver extends Observer
    {
        /**
         * Instantiates a new Simple observer.
         *
         * @param name       the name
         * @param observable the observable
         * @param registry   the registry
         * @throws RemoteException       the remote exception
         * @throws MalformedURLException the malformed url exception
         */
        public SimpleObserver(String name, RemoteObservableIF observable, Registry registry) throws RemoteException, MalformedURLException
        {
            super(name, observable, registry);
        }

        public synchronized void update(Serializable observable, Serializable arg) throws RemoteException
        {
            super.update(observable, arg);
            System.out.println("observable: " + observable + " arg: " + arg);
        }
    }
}
