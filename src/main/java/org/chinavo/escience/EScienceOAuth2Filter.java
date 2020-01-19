package org.chinavo.escience;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EScienceOAuth2Filter {

    final static Logger log = LoggerFactory.getLogger(EScienceOAuth2Filter.class);

    public static Handler signin = ctx -> {
        // 调用对应的登录验证
        if(SecurityContext.isAuthEscience(ctx)) {
           loginFromEscience(ctx);
        } else {
            log.error("unknown authorization server!");
            ctx.json("unknown authorization server");
        }
    };

    /**
     * 科技共享网登录
     */
    static void loginFromEscience(Context ctx) {
        String code = ctx.queryParam("code","");
        log.info("escience auth code is {}", code);
        EScienceConfig c = new EScienceConfig();
        log.info("using escience config {}", c);

        HttpResponse<JsonNode> tokenRes = Unirest.post(c.accessTokenURL)
                .field("client_id", c.clientId)
                .field("client_secret", c.clientSecret)
                .field("redirect_uri", c.redirectURL)
                .field("code", code)
                .field("grant_type", c.grantType)
                .field("scope", c.scope)
                .asJson();
        log.info("escience auth response : {}", tokenRes.getBody().getObject().toString());

        JSONObject tokenJson = tokenRes.getBody().getObject();
        if(!tokenJson.has("access_token")){
            log.error("obtaining access token error");
            ctx.json("escience oauth error");
        }

        String token = tokenJson.getString("access_token");
        String tokenType = tokenJson.getString("token_type");

        log.info("get access token : {}", token);

        HttpResponse<JsonNode> userRes = Unirest.get(c.userInfoURL)
                .header("Authorization",tokenType +" "+token)
                .asJson();

        JSONObject ujson = userRes.getBody().getObject();
        //JSONObject uobj = ujson.getJSONObject("user");
        log.info("get user info {} ", ujson.toString());

        ctx.json(ujson.toMap());
    }
}
