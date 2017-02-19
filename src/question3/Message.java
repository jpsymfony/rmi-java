package question3;

import java.io.Serializable;

public class Message implements Serializable
{
    private InputChannel source;
    private String message;

    public Message(InputChannel source, String message)
    {
        this.source = source;
        this.message = message;
    }

    public InputChannel getSource()
    {
        return this.source;
    }

    public String getMessage()
    {
        return this.message;
    }

    public String toString()
    {
        return "<" + source + "," + message + ">";
    }

}
