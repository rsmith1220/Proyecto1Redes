package connector;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.parts.Localpart;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jxmpp.jid.parts.Part;

public class Initiator {
    public static AbstractXMPPConnection Coneccion(String user, String pass)
            throws SmackException, IOException, XMPPException, InterruptedException {

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

        return connection;
    }

    public static String Deconeccion(AbstractXMPPConnection connection)
            throws SmackException, IOException, XMPPException, InterruptedException {

        connection.disconnect();

        return "Desconectado";

    }

    public static void Creation()
            throws SmackException, IOException, XMPPException, InterruptedException {
        System.out.println("Ingrese su nombre");
        Scanner sc = new Scanner(System.in);
        String usuario = sc.nextLine();

        System.out.println("Ingrese su password");
        String password = sc.nextLine();

        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHost("alumchat.xyz")
                    .setXmppDomain("alumchat.xyz")
                    .setPort(5222)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .build();
            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect();

            AccountManager accountManager = AccountManager.getInstance(connection);

            try {
                if (accountManager.supportsAccountCreation()) {
                    accountManager.sensitiveOperationOverInsecureConnection(true);
                    accountManager.createAccount(Localpart.from(usuario), password);
                    System.out.println("\nSe ha creado su cuenta, ya puede iniciar sesion\n");
                } else {
                    System.out.println("Account creation is not supported by this server");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al crear la cuenta");
            }
            connection.disconnect();

            connection.disconnect();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\n Su cuenta no fue creada");

        }

    }

    public static void eliminar() {

        System.out.println("Ingrese su usuario para confirmar la eliminacion");
        Scanner sc = new Scanner(System.in);
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
            AbstractXMPPConnection connection = new org.jivesoftware.smack.tcp.XMPPTCPConnection(config);
            connection.connect();
            connection.login();

            AccountManager accountManager = AccountManager.getInstance(connection);
            accountManager.deleteAccount();
            System.out.println("Cuenta eliminada");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\n Su cuenta no fue eliminada");
        }
    }
}
