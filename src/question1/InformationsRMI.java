package question1;

import java.util.Properties;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface Informations rmi.
 */
public interface InformationsRMI extends Informations, Remote
{
    /**
     * The constant nomDuService.
     */
    public static final String nomDuService = "informations";

    public String getDate() throws Exception, RemoteException;

    public Properties getProperties() throws Exception, RemoteException;
}