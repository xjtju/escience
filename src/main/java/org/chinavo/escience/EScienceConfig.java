package org.chinavo.escience;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 中国科技资源共享网  *.escience.org.cn
 *  OAuth2.0配置信息
 *
 * 注意: 这跟中国科技网的域名很相似, *.escience.cn
 *
 * @author  xiao jian
 */
public class EScienceConfig {
    final static Logger log = LoggerFactory.getLogger(EScienceConfig.class);

     Properties loadConfig() {
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("escience.properties"));
        } catch (Exception ex) {
           log.error("load escience auth config error : ", ex);
        }
        return prop;
    }
    public EScienceConfig(){
        Properties p = loadConfig();
        clientId = p.getProperty("client_id");
        clientSecret = p.getProperty("client_secret");
        redirectURL = p.getProperty("redirect_uri");
        accessTokenURL = p.getProperty("access_token_uri");
        authorizeURL = p.getProperty("authorize_uri");
        userInfoURL  = p.getProperty("user_info_uri");
        grantType = p.getProperty("grant_type");
        scope = p.getProperty("scope");
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

    /**
     * 构造请求授权码的URL
     */
    String getRequestCodeURL() {
        String uri = authorizeURL+"?response_type=code&redirect_uri=" + redirectURL +"&client_id="+clientId+"&scope="+scope+"&state=cvo";
        log.info("code uri : {}", uri);
        return  uri;
    }
}
