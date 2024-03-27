package be.technobel.pl.config;

import be.technobel.dal.models.entities.User;

import be.technobel.dal.models.enums.enums.Roles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
public class JWTProvider {

    private static final String JWT_SECRET = "7YuQY65WV77AmBjk3qLFT2Q4Pd3TxR4GU25sxfKQ6g92f6aHcnEu5jscP8j35iV7KTvxwtM42X5jMV49Yiv4phx7U37QBK9wr573";
    private static final long EXPIRES_AT = 3_600_000;
    private static final String AUTH_HEADER = "Authorization";

    private static final String TOKEN_PREFIX ="Bearer ";

    private final UserDetailsService userDetailsService;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String username, String email, String firstname, Roles role){

        return TOKEN_PREFIX + JWT.create()
                .withSubject(username)
                .withSubject(email)
                .withClaim("roles", role.toString())
                .withClaim("firstname", firstname)
                .withExpiresAt(Instant.now().plusMillis(EXPIRES_AT))
                .sign(Algorithm.HMAC512(JWT_SECRET));
    }

    public String extractToken(HttpServletRequest request){
        String header = request.getHeader(AUTH_HEADER);

        if(header== null || !header.startsWith(TOKEN_PREFIX))
            return null;
        return header.replaceFirst(TOKEN_PREFIX, "");
    }


    public boolean validateToken(String token){

        try{

            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(JWT_SECRET))
                    .acceptExpiresAt(EXPIRES_AT)
                    // signifie que  le Subject doit être présent pour que la validation réussisse, dans ce cas ci, username et email
                    .withClaimPresence("sub")
                    .withClaimPresence("roles")
                    .build().verify(token);

            String username = jwt.getSubject();

            User user = (User) userDetailsService.loadUserByUsername(username);

            if(!user.isEnabled()){
                return false;
            }
            return true;
        }catch (JWTVerificationException | UsernameNotFoundException ex){
            return false;
        }
    }

    public Authentication createAuthentification(String token){

        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }

}
