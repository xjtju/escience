package org.chinavo.escience;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.chinavo.escience.CVOWeb.*;

public class EntryPoint {
    final static Logger log = LoggerFactory.getLogger(EntryPoint.class);

    //  共享网通行证
    public static Handler gotoEscience = ctx-> {
        SecurityContext.setAuthSrv(ctx, SecurityContext.KEY_AUTH_ESCIENCE);
        EScienceConfig c = new EScienceConfig();
        ctx.redirect(c.getRequestCodeURL());
        log.info("trying login from escience.org.cn");
    };

    //  科技网通行证
    public static Handler gotoCstnet = ctx-> {
        ctx.redirect("https://passport.escience.cn/oauth2/authorize?response_type=code&redirect_uri=http://passport.china-vo.org/signin&client_id=11003");
        log.info("trying login from passport.cstnet.cn");
    };
}
