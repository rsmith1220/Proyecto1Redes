package hello;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
public class HelloWorld {
  public static void main(String[] args) {
    String username = "1220";
    String password = "122001";
    // String recipient = "recipient_username";
    String server = "alumchat.xyz";

    XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
            .setUsernameAndPassword(username, password)
            .setXmppDomain(server)
            .build();

    AbstractXMPPConnection connection = new XMPPTCPConnection(config);


  }
}