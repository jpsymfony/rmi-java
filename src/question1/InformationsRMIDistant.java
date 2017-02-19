package question1;

import java.util.Properties;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
// une implementation du pattern Adaptateur

public class InformationsRMIDistant extends UnicastRemoteObject implements InformationsRMI
{
    private Informations services; // l'adapte

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
