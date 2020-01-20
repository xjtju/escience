package org.chinavo.escience;

public class CstnetConfig extends OAuthConfig{

    public CstnetConfig(String file) {
        super(file);
    }

    public CstnetConfig() {
        super("cstnet.properties");
    }

    @Override
    String getRequestCodeURL() {
        String uri = authorizeURL+"?response_type=code&redirect_uri=" + redirectURL +"&client_id="+clientId+"&state=cvo&theme="+theme;
        return uri;
    }
}
