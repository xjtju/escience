package org.chinavo.escience.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.javalin.http.Context;
import org.chinavo.escience.User;

import java.util.Optional;

public class JWTSample {

    static JWTProvider provider = createHMAC512();

    static JWTProvider createHMAC512() {
        JWTGenerator<User> generator = (user, alg) -> {
            JWTCreator.Builder token = JWT.create()
                    .withClaim("id", user.getUserId())
                    .withClaim("name", user.getUserName());
            return token.sign(alg);
        };

        Algorithm algorithm = Algorithm.HMAC256("very_secret");
        JWTVerifier verifier = JWT.require(algorithm).build();

        return new JWTProvider(algorithm, generator, verifier);
    }

    public static void loginJWT(Context ctx) {
        User user = new User();
        user.setUserId("007");
        user.setUserName("Bambook");

            // generate a token for the user
        String token = provider.generateToken(user);
        JavalinJWT.addTokenToCookie(ctx, token);
            // send the JWT response
        ctx.json(new JWTResponse(token));
    }

    public static void validateJWT(Context ctx) {
        Optional<DecodedJWT> decodedJWT = JavalinJWT.getTokenFromCookie(ctx)
                .flatMap(provider::validateToken);

        if (!decodedJWT.isPresent()) {
            ctx.status(401).result("Missing or invalid token");
        } else {
                ctx.result("Hi " + decodedJWT.get().getClaim("name").asString());
        }
    }
}
