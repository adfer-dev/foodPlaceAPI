package com.adfer.security.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${application.security.jwt.secret-key}")
    private  String secretKey;
	@Value("${application.security.jwt.expiration}")
	private long expiration;
	@Value("${application.security.jwt.refresh-expiration}")
	private long refreshExpiration;
	
    public String extractUserName(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }
    
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    
    private String buildToken (Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
    	return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //token will be valid for 24 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();   
    }
    
    
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, this.expiration);
    }
    
    public String generateRefreshToken(
    	      UserDetails userDetails
    	  ) {
    	    return buildToken(new HashMap<>(), userDetails, this.refreshExpiration);
    }
    
    public boolean isTokenValid (String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    
    private Claims extractAllClaims(String token) throws ExpiredJwtException{
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
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenExpired(String token) {
    	try {
    		return extractExpiration(token).before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
