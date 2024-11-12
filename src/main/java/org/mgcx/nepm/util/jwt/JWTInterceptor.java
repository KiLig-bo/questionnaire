package org.mgcx.nepm.util.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mgcx.nepm.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //获取请求头中的令牌
        String token = request.getHeader("token");
        log.info("当前token为：{}", token);

        Map<String, Object> map = new HashMap<>();
        try {
            JWTUtil.verify(token);
            DecodedJWT verify = JWTUtil.verify(token);
            String username = verify.getClaim("username").asString();
            String password = verify.getClaim("password").asString();
            log.info("解析token");
            log.info("用户username：{}", username);
            log.info("用户password: {}", password);
            log.info("去验证todo...");
            //验证token
            String tokenInRedis = (String) redisService.get(username);
            if (tokenInRedis == null || !token.equals(tokenInRedis)) {
                map.put("msg", "token无效");
                map.put("status", false);
                //响应到前台: 将map转为json
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
                return false;
            }
            else {
                log.info("验证成功");
                map.put("msg", "token有效");
                map.put("status", true);
                //响应到前台: 将map转为json
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
                return true;
            }
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "签名不一致");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "令牌过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不匹配");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg", "失效的payload");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效");
        }
        return false;
    }
}
