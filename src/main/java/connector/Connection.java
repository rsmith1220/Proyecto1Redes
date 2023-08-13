package connector;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException; // Import the exception
import org.sakaiproject.chat2.model.ChatManager;

import java.io.IOException;

import java.util.*;

public class Connection {
    public static void main(String[] args) throws SmackException, IOException, XMPPException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario");
        String user = sc.nextLine();
        System.out.println("Ingrese su password");
        String pass = sc.nextLine();

        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHost("alumchat.xyz")
                    .setXmppDomain("alumchat.xyz")
                    .setPort(5222)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setUsernameAndPassword(user, pass)
                    .build();

            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect(); // Establishes a connection to the server
            connection.login(); // Logs in

            System.out.println("Connected");

            org.jivesoftware.smack.chat2.ChatManager chat = org.jivesoftware.smack.chat2.ChatManager
                    .getInstanceFor(connection);
            EntityBareJid jid = JidCreate.entityBareFrom("ABCD@alumchat.xyz");
            // Chat chat = chatManager.chatWith(jid);
            Chat chat2 = chat.chatWith(jid);
            chat2.send("Hello!");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
