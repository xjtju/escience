package org.chinavo.escience;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CVOWeb {
    final static Logger log = LoggerFactory.getLogger(CVOWeb.class);

    public final static Javalin app = Javalin.create(
            config -> {
                // Static resource handling is done after all dynamic endpoints matching,
                // meaning your self-defined endpoints have higher priority
                config.addStaticFiles("F:\\workspaces\\cvo\\escience\\src\\web", Location.EXTERNAL);
                config.enableCorsForAllOrigins();   // 接收移动端的请求
                config.registerPlugin(new RouteOverviewPlugin("/debug/routs"));
            }
    );

    public static void start(){
        app.get("/", ctx -> ctx.redirect("index.html") );

        app.get("/signin", EScienceOAuth2Filter.signin);

        app.get("/loginEscience", EntryPoint.gotoEscience);
        app.get("/loginCstnet", EntryPoint.gotoCstnet);

        app.start(8080);
        log.info("[CVO] The Javalin server is ready");
    }

    public static void stop(){
        app.stop();
        log.info("[CVO] The Javalin server has been shutdown.");
    }
}
