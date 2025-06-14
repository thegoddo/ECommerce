package lnct.project.ECommerce.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String KEY;

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }



    public  <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims =extractAllClaims(token);
        return claimResolver.apply(claims);
    }



    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);

    }


    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpriration(token).before(new Date());
    }

    private Date extractExpriration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L))
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();

    }



    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSingInKey() {
        byte[] keyByte= Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
