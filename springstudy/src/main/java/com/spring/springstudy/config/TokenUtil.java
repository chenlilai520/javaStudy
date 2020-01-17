package com.spring.springstudy.config;

import com.spring.springstudy.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

/**
 * @author chenlilai
 * @title: TokenUtil
 * @projectName javaStudy
 * @description:
 * @date 2020/1/816:32
 */
public class TokenUtil {


    @Value("${secretKey}")
    private String secretKey;

    @Value("${tokenValidityInSeconds}")
    private long tokenValidityInSeconds;

    public String craeteToken(User user){

        long now= System.currentTimeMillis();

       return Jwts.builder()
                .claim("sss",null) //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setIssuedAt(new Date())//iat: jwt的签发时间
                .setExpiration(new Date(now+tokenValidityInSeconds))//设置签名使用的签名算法和签名使用的秘钥
                .setSubject(user.getId().toString())//代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }


    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(secretKey)                 //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();     //设置需要解析的jwt
        return claims;
    }
}
