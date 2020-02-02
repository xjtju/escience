package org.chinavo.escience.jwt;

import com.auth0.jwt.algorithms.Algorithm;

@FunctionalInterface
public interface JWTGenerator <T> {
    String generate(T obj, Algorithm algorithm);
}