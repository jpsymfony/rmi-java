package question1;

import java.rmi.*;

public class Client
{
    public static void main(String[] args)
    {
        System.setSecurityManager(new RMISecurityManager());

        try {
            Informations services = (Informations) Naming.lookup("rmi://localhost:1099/informations");
            System.out.println(services.getDate());
            java.util.Properties props = services.getProperties();
            System.out.println(props.getProperty("os.arch"));
        } catch (Exception e) {
            System.out.println("Erreur d'acces a un objet distant");
            System.out.println(e.toString());
        }
    }
}
