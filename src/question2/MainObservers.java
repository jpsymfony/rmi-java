package question2;

import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;


import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.io.Serializable;

public class MainObservers
{
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
