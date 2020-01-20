package org.chinavo.escience;

import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityContext {
    final static Logger log = LoggerFactory.getLogger(SecurityContext.class);

    public static final String KEY_CURRENT_USER = "current_user";
    public static final String KEY_AUTH_SERVER=  "auth_server";
    public static final String KEY_AUTH_ESCIENCE=  "as_escience";
    public static final String KEY_AUTH_CSTNET=  "as_cstnet";
    //public static final String KEY_CONF_ESCIENCE = "escience.properties";
    //public static final String KEY_CONF_CSTNET = "cstnet.properties";

    public static String setAuthSrv(Context ctx, String s) {
        ctx.sessionAttribute(KEY_AUTH_SERVER, s);
        log.info("new login will come from {}.", s);
        return s;
    }

    public static String getAuthSrv(Context ctx){
        String a = ctx.sessionAttribute(KEY_AUTH_SERVER);
        if(a!=null) return a;
        else return "inner";
    }

    public static boolean isAuthEscience(Context ctx) {
        String o = getAuthSrv(ctx);
        log.info("login from {}.", o);
        return KEY_AUTH_ESCIENCE.equalsIgnoreCase(o);
    }

    public static boolean isAuthCstnet(Context ctx) {
        String o = getAuthSrv(ctx);
        log.info("login from {}.", o);
        return KEY_AUTH_CSTNET.equalsIgnoreCase(o);
    }

    public static User setCurrentUser(Context ctx, User u) {
        ctx.sessionAttribute(KEY_CURRENT_USER, u);
        return u;
    }

    public static User getCurrentUser(Context ctx){
         return ctx.sessionAttribute(KEY_CURRENT_USER);
    }
}
