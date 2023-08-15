package connector;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.NotLoggedInException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.MucEnterConfiguration;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.muc.MultiUserChatException.MucNotJoinedException;
import org.jivesoftware.smackx.muc.MultiUserChatException.NotAMucServiceException;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

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

    public static void recibir(AbstractXMPPConnection connection) {
        org.jivesoftware.smack.chat2.ChatManager chatManager = org.jivesoftware.smack.chat2.ChatManager
                .getInstanceFor(connection);

        chatManager.addIncomingListener((from, message, chat) -> {
            String sender = from.getLocalpartOrNull().toString();
            String messageText = message.getBody();
            System.out.println("Received message from " + sender + ": " + messageText);
        });

    }

    public static void ContactoSend(AbstractXMPPConnection connection)
            throws NotLoggedInException, NoResponseException, XMPPErrorException,
            NotConnectedException, InterruptedException, XmppStringprepException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario");
        String targetJID = sc.nextLine();
        Roster roster = Roster.getInstanceFor(connection);
        BareJid jid = JidCreate.bareFrom(targetJID + "@alumchat.xyz");

        System.out.println("Ingrese su nombre");
        String tunombre = sc.nextLine();

        roster.addRosterListener(new RosterListener() {
            @Override
            public void entriesAdded(Collection<Jid> addresses) {
                // Handle new entries
            }

            @Override
            public void entriesUpdated(Collection<Jid> addresses) {
                // Handle updated entries
            }

            @Override
            public void entriesDeleted(Collection<Jid> addresses) {
                // Handle deleted entries
            }

            @Override
            public void presenceChanged(Presence presence) {
                if (presence.getType() == Presence.Type.subscribe) {
                    BareJid fromJID = presence.getFrom().asBareJid();

                    try {
                        // Accepting the request
                        roster.createItemAndRequestSubscription(fromJID, tunombre, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Send subscription request
        roster.createItemAndRequestSubscription(jid, "Nickname", null);
    }

    public static void Personas(AbstractXMPPConnection connection)
            throws SmackException, IOException, XMPPException, InterruptedException {

        try {

            Roster roster = Roster.getInstanceFor(connection);
            roster.addRosterListener(new RosterListener() {
                @Override
                public void entriesAdded(java.util.Collection<Jid> addresses) {
                    // Handle new entries
                }

                @Override
                public void entriesUpdated(java.util.Collection<Jid> addresses) {
                    // Handle updated entries
                }

                @Override
                public void entriesDeleted(java.util.Collection<Jid> addresses) {
                    // Handle deleted entries
                }

                @Override
                public void presenceChanged(Presence presence) {
                    // Handle presence changes
                }
            });

            roster.reload();
            for (RosterEntry entry : roster.getEntries()) {
                System.out.println("User: " + entry.getJid());
                Presence presence = roster.getPresence(entry.getJid());
                System.out.println("Presence: " + presence.getStatus());
                System.out.println("Availability: " + presence.getMode());
                System.out.println("-------------------------------");
            }

            connection.disconnect();
        } catch (SmackException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveVCard(AbstractXMPPConnection connection)
            throws NoResponseException, XMPPErrorException, NotConnectedException, InterruptedException {

        if (ServiceDiscoveryManager.getInstanceFor(connection).serverSupportsFeature(VCard.NAMESPACE)) {
            // Server supports vCards. You can now try to retrieve it.
            VCardManager vCardManager = VCardManager.getInstanceFor(connection);
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el nombre del contacto");
            String nombre = sc.nextLine();
            String userJID = nombre + "@alumchat.xyz";
            try {
                VCard vCard = vCardManager.loadVCard(JidCreate.entityBareFrom(userJID));

                // Print out some of the info from the vCard
                System.out.println("Full Name: " + vCard.getField("FN"));
                System.out.println("Nickname: " + vCard.getNickName());
                System.out.println("Email: " + vCard.getEmailHome());
                System.out.println("Profile Photo: " + (vCard.getAvatar() != null ? "Exists" : "Does not exist"));
                // ... And so on. The VCard class has many methods to get various bits of info.

            } catch (Exception e) {
                System.err.println("Error retrieving vCard: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Server doesn't support vCards");
        }
    }

    public static void MUC(AbstractXMPPConnection connection)
            throws XmppStringprepException, NotAMucServiceException,
            XMPPErrorException, NoResponseException, NotConnectedException, InterruptedException,
            MucNotJoinedException {

        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del grupo");
        String grupo = sc.nextLine();
        System.out.println("Ingrese su nombre");
        String nombre = sc.nextLine();

        EntityBareJid mucJid = JidCreate.entityBareFrom(grupo + "@conference.alumchat.xyz");
        Resourcepart nickname = Resourcepart.from(nombre);

        // Get the MUC manager
        MultiUserChatManager mucManager = MultiUserChatManager.getInstanceFor(connection);
        MultiUserChat muc = mucManager.getMultiUserChat(mucJid);

        // Create a MucEnterConfiguration
        MucEnterConfiguration.Builder mucEnterConfigBuilder = muc.getEnterConfigurationBuilder(nickname)
                .requestNoHistory(); // Here, we are using requestNoHistory(), but you can configure as you need.

        MucEnterConfiguration mucEnterConfiguration = mucEnterConfigBuilder.build();

        // Join the MUC
        muc.join(mucEnterConfiguration);
        do {
            System.out.println("1. Ver chat\n 2.Salir del grupo");
            opcion = sc.nextInt();
            muc.addMessageListener(new MessageListener() {
                @Override
                public void processMessage(Message message) {
                    if (message.getBody() != null) {
                        System.out.println(message.getFrom() + ": " + message.getBody());
                    }
                }
            });

            System.out.println("Escriba su mensaje");
            String mensaje = sc.nextLine();

            muc.sendMessage(mensaje);
        } while (opcion != 2);
        muc.leave();

    }

}
