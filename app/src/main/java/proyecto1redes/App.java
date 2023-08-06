
package proyecto1redes;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws SmackException, IOException, XMPPException, InterruptedException {
        XMPPTCPConnectionConfiguration config = null;
        System.setProperty("smack.debugEnabled", "false"); // Disable loading smack-config.xml

        try {
            config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("1220","122001")
                    .setXmppDomain("alumchat.xyz")
                    .setHost("alumchat.xyz")
                    .build();
        } catch (XmppStringprepException e) {
            throw new RuntimeException(e);
        }
        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
        connection.connect(); //Establishes a connection to the server
        connection.login(); //Logs in
    }
}
