package hello;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException; // Import the exception

public class HelloWorld {
    public static void main(String[] args) {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("1220", "122001")
                .setXmppDomain("alumchat.xyz")
                .setHost("alumchat.xyz")
                .build();
        try {
            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect();
            connection.login();
            System.out.println("Connected and logged in!");
        } catch (XmppStringprepException e) { // Catch XmppStringprepException
            e.printStackTrace();
            System.out.println("Error during XMPP string preparation.");
        } catch (Exception e) { // Catch other exceptions
            e.printStackTrace();
            System.out.println("Failed to connect or log in.");
        }
    }
}
