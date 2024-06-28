package Saludity.User.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@Slf4j
public class JwtService {

    private static final  String SECRET_KEY="f6ef2dd51bd6bfd39034b9aaabff6a568f5415c683425c21281103f9e39a9c0c";




    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        System.out.println("Se genera el token ");
        System.out.println(generateToken(new HashMap<>(),userDetails));
        return  generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isvalidateToken(String token, UserDetails userDetails) {
        final String username=extractUsername(token);
        System.out.println(username.equals(userDetails.getUsername()) && isTokenExprired(token));
        return (username.equals(userDetails.getUsername()) && isTokenExprired(token));
    }

    private boolean isTokenExprired(String token) {
        System.out.println(!extractExpiration(token).before(new Date()));
        return !extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }



}
