package question1;

import java.util.Properties;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
// une implementation du pattern Adaptateur

/**
 * The type Informations rmi distant.
 */
public class InformationsRMIDistant extends UnicastRemoteObject implements InformationsRMI
{
    private Informations services; // l'adapte

    /**
     * Instantiates a new Informations rmi distant.
     *
     * @param services the services
     * @throws RemoteException the remote exception
     */
    public InformationsRMIDistant(Informations services) throws RemoteException
    {
        this.services = services;
    }

    public String getDate() throws Exception, RemoteException
    {
        return services.getDate();
    }

    public Properties getProperties() throws Exception, RemoteException
    {
        return services.getProperties();
    }
}
