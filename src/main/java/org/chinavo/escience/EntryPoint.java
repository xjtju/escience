package org.chinavo.escience;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntryPoint {
    final static Logger log = LoggerFactory.getLogger(EntryPoint.class);

    //  共享网通行证
    public static Handler gotoEscience = ctx-> {
        SecurityContext.setAuthSrv(ctx, SecurityContext.KEY_AUTH_ESCIENCE);
        OAuthConfig c = new EScienceConfig();
        ctx.redirect(c.getRequestCodeURL());
        log.info("trying login from escience.org.cn");
    };

    //  科技网通行证
    public static Handler gotoCstnet = ctx-> {
        SecurityContext.setAuthSrv(ctx, SecurityContext.KEY_AUTH_CSTNET);
        OAuthConfig c = new CstnetConfig();
        ctx.redirect(c.getRequestCodeURL());
        log.info("trying login from passport.cstnet.cn");
    };
}
