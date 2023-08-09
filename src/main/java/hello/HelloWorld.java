package hello;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
public class HelloWorld {
  public static void main(String[] args) {
    Greeter greeter = new Greeter();
    System.out.println(greeter.sayHello());
    XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
  .setUsernameAndPassword("1220","122001")
  // .setXmppDomain("alumchan.xyz")
  .setHost("alumchan.xyz")
  .build();
  try {
    AbstractXMPPConnection connection = new XMPPTCPConnection(config);
connection.connect(); //Establishes a connection to the server
connection.login(); //Logs in
System.out.println("Facts");

  } catch (Exception e) {
    // TODO: handle exception\
    System.out.println("nel");
  }
  }
}