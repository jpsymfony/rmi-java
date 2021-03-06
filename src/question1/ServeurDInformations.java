package question1;

import java.rmi.Naming;

/**
 * The type Serveur d informations.
 */
public class ServeurDInformations
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception
    {
        Informations services = new Services();
        InformationsRMI informationsRMIDistant = new InformationsRMIDistant(services);

        System.out.println("Objet Compte enregistre dans RMIregistry");

        Naming.rebind("rmi://localhost:1099/informations", informationsRMIDistant);
    }
}