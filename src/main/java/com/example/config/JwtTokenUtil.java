package com.example.config;

import java.security.Key;
import java.util.Date;

import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import static sun.security.x509.CertificateX509Key.KEY;

@Component
public class JwtTokenUtil {

    private String jwtTokenSecret;
    private long jwtTokenExpiration;

    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User)authentication.getPrincipal();
        Key key = Keys.hmacShaKeyFor("IrE9Rig9YqlkyoFZgeObr1Lbi8ZWR86UUDtLeFaqpAsRRf8H1zxoKQEXOcoHw9n0".getBytes());
        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
        String token = Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).compact();

        return token;
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtTokenSecret)
                    .parseClaimsJws(token);
            return true;
        }catch(UnsupportedJwtException exp) {
            System.out.println("claimsJws argument does not represent Claims JWS" + exp.getMessage());
        }catch(MalformedJwtException exp) {
            System.out.println("claimsJws string is not a valid JWS" + exp.getMessage());
        }catch(SignatureException exp) {
            System.out.println("claimsJws JWS signature validation failed" + exp.getMessage());
        }catch(ExpiredJwtException exp) {
            System.out.println("Claims has an expiration time before the method is invoked" + exp.getMessage());
        }catch(IllegalArgumentException exp) {
            System.out.println("claimsJws string is null or empty or only whitespace" + exp.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims =Jwts.parser()
                .setSigningKey(jwtTokenSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }
}