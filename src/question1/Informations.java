package question1;

import java.util.Properties;

/**
 * @author nsy102
 * @see System;getProperties, calendar
 */
public interface Informations
{
    /**
     * Obtention de la date courante.
     *
     * @return la date lisible
     * @throws java.lang.Exception
     */
    public String getDate() throws Exception;

    /**
     * Obtention des proprietes "systeme"
     *
     * @return la liste des proprietes de cette machine virtuelle
     * @throws java.lang.Exception
     */
    public Properties getProperties() throws Exception;
}
