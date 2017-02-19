package question1;

import java.util.Properties;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InformationsRMI extends Informations, Remote
{
    public static final String nomDuService = "informations";

    public String getDate() throws Exception, RemoteException;

    public Properties getProperties() throws Exception, RemoteException;
}