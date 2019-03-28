package com.zhh.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhh.common.enums.BizExceptionEnum;
import com.zhh.common.exception.BizException;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Description: JWT工具类
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/27 13:40
 */
public class JwtUtil {
    // JWT密钥
    private static final String SECRET = "shiro-jwt";

    // 过期时间:5min
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 创建token
     * @param username
     * @return 加密的token
     */
    public static String createToken(String username){
        try {
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("username",username)
                .withExpiresAt(expireDate)
                .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new BizException(BizExceptionEnum.UNSUPPORTEDENCODING_EXCEPTION);
        }
    }

    /**
     * 验证token
     * @param token
     * @param username
     * @return 是否正确
     */
    public static boolean verifyToken(String token, String username){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            jwtVerifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            throw new BizException(BizExceptionEnum.UNSUPPORTEDENCODING_EXCEPTION);
        }catch (TokenExpiredException e){
            throw new BizException(BizExceptionEnum.TOKEN_EXPIRED_EXCEPTION);
        }
    }

    /**
     *解析token获取用户名，无须解密
     * @param token
     * @return 用户名username
     */
    public static String getUsername(String token){
        try {
            DecodedJWT decodeJWT = JWT.decode(token);
            return decodeJWT.getClaim("username").asString();
        }catch(JWTDecodeException e){
            throw new BizException(BizExceptionEnum.JWTDECODE_EXCEPTION);
        }
    }

}
