package com.adfer.security.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "np9/XGT9E3y/fC/bJCPb2mCB46aXiIIqekqT+MICDeXhA6eBXx+WqjiRFuVTJi8Cs+TBqo86C2P8HCpbE6sEJf+dNhGHG8b9+QnfHSnRzwP1DkzcmEBWGrde9yLrtYXKPSIgWwMPgkF9+NCNOf9xSsCeE2R0N9Nh34155z+7oXEWa7QBTxhr98L1FfdgLjrRIXU5EFKJf1bycgn5KwQVk61TE9xexptjXVzvA9A9tI3Z3L94hcBt5hbluwEiltHJvmvfT6u5fN1sh5Nmm0yrZsSaBGCxjaUW+wGfVDYdI+0AZwPrgTipoWUUtFJL8D0yWlOnicu7M8sMdyea9b3lNFNnSi31tICyLddmuroy3fw=";
    
    String extractUserName(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }
    
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //token will be valid for 24 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();          
    }
    
    public boolean isTokenValid (String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenExpired(String token) {
    	return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
