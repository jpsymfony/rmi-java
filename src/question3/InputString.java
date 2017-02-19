package question3;


public class InputString implements InputChannel
{
    private static int nextValue = 1;
    private int hashVal;
    private String str;

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
