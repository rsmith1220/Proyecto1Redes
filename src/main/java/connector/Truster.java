package connector;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class Truster implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        // Accept all client certificates
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        // Accept all server certificates
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
