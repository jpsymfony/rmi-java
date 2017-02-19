package question1;

import java.util.Properties;
import java.util.Calendar;
import java.text.DateFormat;

public class Services implements Informations
{
    public String getDate() throws Exception
    {
        Calendar c = Calendar.getInstance();
        DateFormat df = DateFormat.getInstance();
        return df.format(c.getTime()).toString();
    }

    public Properties getProperties() throws Exception
    {
        return System.getProperties();
    }
}