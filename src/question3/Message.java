package question3;

import java.io.Serializable;

/**
 * The type Message.
 */
public class Message implements Serializable
{
    private InputChannel source;
    private String message;

    /**
     * Instantiates a new Message.
     *
     * @param source  the source
     * @param message the message
     */
    public Message(InputChannel source, String message)
    {
        this.source = source;
        this.message = message;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public InputChannel getSource()
    {
        return this.source;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage()
    {
        return this.message;
    }

    public String toString()
    {
        return "<" + source + "," + message + ">";
    }

}
