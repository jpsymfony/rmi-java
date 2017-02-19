package question3;

public class TestsRecipientList extends junit.framework.TestCase
{
    public void test_en_local_Patron_StoreAndForward() throws Exception
    {
        try {
            RecipientList recipient = new RecipientListImpl("recipientA");
            Receiver a = new Receiver("A");
            Receiver b = new Receiver("B");
            Receiver c = new Receiver("C");
            Receiver d = new Receiver("D");
            Channel[] destinations1 = new Channel[]{a, b, c, d};

            InputChannel in1 = new InputString("input1");
            recipient.addRoute(in1, destinations1);

            Message msg = new Message(in1, "test1");
            recipient.sendMessage(msg);
            Thread.sleep(50);
            System.out.println("recipient" + recipient);

            assertTrue(" message bien recu ?", a.getReceived().getMessage().equals("test1"));
            assertTrue(" message bien recu ?", b.getReceived().getMessage().equals("test1"));
            assertTrue(" message bien recu ?", c.getReceived().getMessage().equals("test1"));
            assertTrue(" message bien recu ?", d.getReceived().getMessage().equals("test1"));

            InputChannel in2 = new InputString("input2");
            Receiver e = new Receiver("E");
            Receiver f = new Receiver("F");
            Receiver g = new Receiver("G");
            Receiver h = new Receiver("H");

            recipient.addRoute(in2, new Channel[]{e, f, g, h});
            Message msg2 = new Message(in2, "test2");
            recipient.sendMessage(msg2);
            Thread.sleep(50);
            assertTrue(" message bien recu ?", e.getReceived().getMessage().equals("test2"));
            assertTrue(" message bien recu ?", f.getReceived().getMessage().equals("test2"));
            assertTrue(" message bien recu ?", g.getReceived().getMessage().equals("test2"));
            assertTrue(" message bien recu ?", h.getReceived().getMessage().equals("test2"));


            recipient.sendMessage(msg);
            Thread.sleep(50);
            assertTrue(" message bien recu ?", a.getReceived().getMessage().equals("test1"));
            assertTrue(" message bien recu ?", b.getReceived().getMessage().equals("test1"));
            assertTrue(" message bien recu ?", c.getReceived().getMessage().equals("test1"));
            assertTrue(" message bien recu ?", d.getReceived().getMessage().equals("test1"));

            recipient.sendMessage(msg2);
            Thread.sleep(50);
            assertTrue(" message bien recu ?", e.getReceived().getMessage().equals("test2"));
            assertTrue(" message bien recu ?", f.getReceived().getMessage().equals("test2"));
            assertTrue(" message bien recu ?", g.getReceived().getMessage().equals("test2"));
            assertTrue(" message bien recu ?", h.getReceived().getMessage().equals("test2"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
