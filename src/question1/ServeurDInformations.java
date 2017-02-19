package question1;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Properties;

public class ServeurDInformations
{
    public static void main(String[] args) throws Exception
    {
        Informations services = new Services();
        InformationsRMI informationsRMIDistant = new InformationsRMIDistant(services);

        System.out.println("Objet Compte enregistre dans RMIregistry");

        Naming.rebind("rmi://localhost:1099/informations", informationsRMIDistant);
    }
}