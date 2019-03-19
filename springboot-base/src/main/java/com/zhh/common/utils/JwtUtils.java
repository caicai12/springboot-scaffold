package com.zhh.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final String JWT_SECRET = "4ewqiouewqenjcxz";

    /**
     * 构建jwt
     */
    public static String createJWT(String name, String userId, String role,
                                   String audience, String issuer, long ttlmillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("unique_name", name)
                .claim("userid", userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (ttlmillis >= 0) {
            long expMillis = nowMillis + ttlmillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    /**
     * 解析jwt数据
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            //生成签名密钥
            byte[] apiKeySecretBytes = JWT_SECRET.getBytes();
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}
