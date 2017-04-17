package question2;

import java.rmi.RMISecurityManager;

/**
 * The type Main observable.
 */
public class MainObservable
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

        RemoteObservableIF observable = new Observable();
    }
}
