package question3;

/**
 * The type Input string.
 */
public class InputString implements InputChannel
{
    private static int nextValue = 1;
    private int hashVal;
    private String str;

    /**
     * Instantiates a new Input string.
     *
     * @param str the str
     */
    public InputString(String str)
    {
        this.str = str;
        this.hashVal = nextValue++;
    }

    public int hashCode()
    {
        return this.hashVal;
    }

    public boolean equals(Object obj)
    {
        InputString i = (InputString) obj;
        return this.hashVal == i.hashVal;
    }

    public String toString()
    {
        return "InputString : " + str;
    }
}
