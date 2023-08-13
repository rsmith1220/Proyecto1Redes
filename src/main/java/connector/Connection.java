package connector;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException; // Import the exception
import org.sakaiproject.chat2.model.ChatManager;

import java.io.IOException;

public class Connection {
    public static void main(String[] args) throws SmackException, IOException, XMPPException, InterruptedException {

        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHost("alumchat.xyz")
                    .setXmppDomain("alumchat.xyz")
                    .setPort(5222)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setUsernameAndPassword("rebecca", "122001")
                    .build();

            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect(); // Establishes a connection to the server
            connection.login(); // Logs in

            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
