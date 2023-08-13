package com.alura.hotelalura.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;

import java.security.Key;
import java.util.Date;

public class JWTGenerate
{
    private static final Key KEY_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Long EXPIRATE_KEY = 86400000L; // 24 hora en milisegundos

    @Getter
    private static String myCliente;

    public static String createTokenCliente(String dni)
    {
        Date date = new Date(System.currentTimeMillis()+EXPIRATE_KEY);
        return Jwts.builder()
                .setSubject(dni.trim())
                .setExpiration(date)
                .signWith(KEY_SECRET)
                .compact();

    }



    public static Boolean validateToken(String token)
    {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                                                      .setSigningKey(KEY_SECRET)
                                                      .build()
                                                      .parseClaimsJws(token);
           Claims claims = claimsJws.getBody();
           Date dateExpiration = claims.getExpiration();
           myCliente = claims.getSubject();
           return !dateExpiration.before(new Date());
        }catch (SignatureException ex) {return false;}
    }

}
