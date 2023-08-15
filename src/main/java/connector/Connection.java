package connector;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException; // Import the exception
import org.sakaiproject.chat2.model.ChatManager;

import java.io.IOException;

import java.util.*;

public class Connection {
    public static void main(String[] args) throws SmackException, IOException, XMPPException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("Bienvenido! Elija una opcion");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Crear usuario");
            System.out.println("3. Exit");
            System.out.print("Opcion: ");
            choice = sc.nextInt();
            performAction(choice);
        } while (choice != 3); // exit

        System.out.println("Disconnected");

    }

    public static void performAction(int choice)
            throws SmackException, IOException, XMPPException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case 1:
                System.out.println("Ingrese su nombre de usuario");
                String user = sc.nextLine();
                System.out.println("Ingrese su password");
                String pass = sc.nextLine();

                AbstractXMPPConnection iniciado = Initiator.Coneccion(user, pass);
                System.out.println("Se ha iniciado sesion");
                do {

                    System.out
                            .println(
                                    "Desea mandar un mesnaje \n 1.Privado\n 2.Chat room\n 3.Borrar cuenta\n 4.Ver contactos\n 5.Agregar contacto\n 6.Ver detalles de contacto\n 7.Cerrar sesion\n");

                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            // Send a message
                            String result = Mensajes.envio(iniciado);

                            System.out.println(result);
                            break;
                        case 2: // chat room
                            break;
                        case 3:// borrar
                            Initiator.eliminar();
                            break;
                        case 4:// ver usuarios
                            Mensajes.Personas(iniciado);
                            break;
                        case 5: // add contact
                            Mensajes.ContactoSend(iniciado);
                            break;
                        case 6:
                            Mensajes.retrieveVCard(iniciado);
                        case 7:// desconectarse
                            Initiator.Deconeccion(iniciado);
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta");
                    }

                } while (choice != 7);

                break;
            case 2:
                System.out.println("Creando cuenta");

                Initiator.Creation();

                break;
            case 3:

                break;

            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

}
