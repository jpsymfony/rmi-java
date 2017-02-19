package question1;

public class ServicesTest extends junit.framework.TestCase
{
    protected void setUp() throws java.lang.Exception
    {

    }

    protected void tearDown() throws java.lang.Exception
    {

    }

    public void test1() throws java.lang.Exception
    {
        question1.Services s = new question1.Services();
        assertNotNull(s.getDate());
        java.util.Properties props = s.getProperties();
        assertNotNull(props);
        // usage en local ou a distance si l'os des 2 machines sont identiques:
        assertEquals(props.getProperty("os.arch"), System.getProperties().getProperty("os.arch"));
        // par exemple le serveur rmi est sous mac :
        assertEquals(props.getProperty("os.arch"),"x86_64");
    }
}
