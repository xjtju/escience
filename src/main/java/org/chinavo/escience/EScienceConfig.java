package org.chinavo.escience;

public class EScienceConfig extends OAuthConfig{

    public EScienceConfig(String file) {
        super(file);
    }

    public EScienceConfig() {
        super("escience.properties");
    }
}
