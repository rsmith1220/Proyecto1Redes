package connector;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.roster.Roster;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.sakaiproject.chat2.model.ChatManager;

import java.io.IOException;
import java.util.*;

public class Mensajes {
    public static String envio(AbstractXMPPConnection connection)
            throws SmackException, IOException, XMPPException, InterruptedException {
        org.jivesoftware.smack.chat2.ChatManager chat = org.jivesoftware.smack.chat2.ChatManager
                .getInstanceFor(connection);

        chat.addIncomingListener(new IncomingChatMessageListener() {
            @Override
            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                System.out.println("New message from " + from + ": " + message.getBody());
            }
        });

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la persona a la que va dirigida el mensaje");
        String nombre = sc.nextLine();
        EntityBareJid jid = JidCreate.entityBareFrom(nombre + "@alumchat.xyz");
        // Chat chat = chatManager.chatWith(jid);
        Chat chat2 = chat.chatWith(jid);

        System.out.println("Ingrese un mensaje");
        String mensaje = sc.nextLine();
        chat2.send(mensaje);

        return "Mensaje enviado";
    }

}
