package com.example.bookstore_backend.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.stereotype.Component;
@Component
public class TokenUtil {
    private static final String TOKEN_SECRET = "Dhanshri";
    public String createToken(Long userId) {
        try {
// To Set Algorithm
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("user_Id", userId)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
// Log Token Signing Failed
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Long decodeToken(String token)
    {
        Long user_Id;
//For Verification Algorithm
        Saml2RelyingPartyProperties.AssertingParty.Verification verification = null;
        try {
            verification = (Saml2RelyingPartyProperties.AssertingParty.Verification) JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        JWTVerifier jwtverifier= ((Verification) verification).build();
//To Decode token
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim claim=decodedjwt.getClaim("user_Id");
        user_Id=claim.asLong();
        return user_Id;
    }
}
