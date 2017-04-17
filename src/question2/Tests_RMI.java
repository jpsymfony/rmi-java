package question2;

import question2.Observable;
import question2.Observer;
import question2.RemoteObservableIF;

import java.rmi.registry.*;
import java.util.Properties;

/**
 * The type Tests rmi.
 */
public class Tests_RMI extends junit.framework.TestCase
{
    protected void setUp()
    { // throws java.lang.Exception{
        java.util.Properties p = System.getProperties();
        p.put("java.rmi.dgc.leaseValue", "1500"); // 1500 ms de bail par defaut
        System.setProperties(p);
    }

    /**
     * Test observable observer.
     */
    public void test_observable_observer()
    {
        Registry registry = null;
        Observable observable = null;
        Observer obsl = null;

        try {
            registry = java.rmi.registry.LocateRegistry.createRegistry(1177);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            observable = new Observable(registry); // ajout du service
        } catch (Exception e) {
            fail("creation de observable impossible ??? " + e.getMessage());
        }

        // installation d'un "observateur" aupres de cet "observable"
        try {
            obsl = new Observer("obs1", observable, registry);
            registry.rebind("obs1", obsl);
        } catch (Exception e) {
            fail("rebind d'un observer impossible ???" + e.getMessage());
        }

        try {
            observable.notifyObservers("test");
            assertNotNull("notification inexistante ? ", obsl.getLastArg());
            assertTrue("notification inexistante ? ", obsl.getLastArg().equals("test"));
        } catch (Exception e) {
            fail("addObserver impossible ???" + e.getMessage());
        }

        try {
            registry.unbind(RemoteObservableIF.OBSERVABLE_NAME);
        } catch (Exception e) {
        }
        try {
            java.rmi.server.UnicastRemoteObject.unexportObject(observable, true);
        } catch (Exception e) {
        }
        try {
            registry.unbind("obs1");
        } catch (Exception e) {
        }
        try {
            java.rmi.server.UnicastRemoteObject.unexportObject(obsl, true);
        } catch (Exception e) {
        }
        try {
            java.rmi.server.UnicastRemoteObject.unexportObject(registry, true);
        } catch (Exception e) {
        }
    }
}
