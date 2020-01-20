package org.chinavo.escience;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 *  OAuth2.0通用配置信息
 *
 * 注意：以下两个认证中心很相似
 * 中国科技资源共享网  *.escience.org.cn
 * 中国科技网通行证, *.escience.cn
 *
 * @author  xiao jian
 */
public class OAuthConfig {
    final static Logger log = LoggerFactory.getLogger(OAuthConfig.class);

     Properties loadConfig(String file) {
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception ex) {
           log.error("load OAuth config error : ", ex);
        }
        return prop;
    }
    public OAuthConfig(String file){
        Properties p = loadConfig(file);
        clientId = p.getProperty("client_id");
        clientSecret = p.getProperty("client_secret");
        redirectURL = p.getProperty("redirect_uri");
        accessTokenURL = p.getProperty("access_token_URL");
        authorizeURL = p.getProperty("authorize_URL");
        userInfoURL  = p.getProperty("user_info_uri");
        grantType = p.getProperty("grant_type");
        scope = p.getProperty("scope");
        theme = p.getProperty("theme");
    }
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    String clientId;
    String clientSecret;
    String redirectURL;
    String accessTokenURL;
    String authorizeURL;
    String userInfoURL;
    String grantType;
    String scope;
    String theme;  //only for passport.cstnet.cn

    /**
     * 构造请求授权码的URL
     */
    String getRequestCodeURL() {
        String uri = authorizeURL+"?response_type=code&redirect_uri=" + redirectURL +"&client_id="+clientId+"&scope="+scope+"&state=cvo";
        log.info("code uri : {}", uri);
        return  uri;
    }
}
