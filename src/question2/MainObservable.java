package question2;

import java.rmi.RMISecurityManager;

public class MainObservable
{
    public static void main(String[] arg) throws Exception
    {
        System.setSecurityManager(new RMISecurityManager());

        RemoteObservableIF observable = new Observable();
    }
}
