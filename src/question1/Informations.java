package question1;

import java.util.Properties;

/**
 * The interface Informations.
 *
 * @author nsy102
 * @see System;getProperties, calendar
 */
public interface Informations
{
    /**
     * Obtention de la date courante.
     *
     * @return la date lisible
     * @throws Exception the exception
     */
    public String getDate() throws Exception;

    /**
     * Obtention des proprietes "systeme"
     *
     * @return la liste des proprietes de cette machine virtuelle
     * @throws Exception the exception
     */
    public Properties getProperties() throws Exception;
}
