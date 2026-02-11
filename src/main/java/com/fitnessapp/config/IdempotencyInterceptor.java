package com.fitnessapp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class IdempotencyInterceptor implements HandlerInterceptor {

    private static final String HEADER = "Idempotency-Key";
    private final StringRedisTemplate redisTemplate;

    public IdempotencyInterceptor(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String key = request.getHeader(HEADER);
        if (key == null || key.isBlank()) {
            return true;
        }
        Boolean stored = redisTemplate.opsForValue().setIfAbsent("idem:" + key, "1", Duration.ofHours(1));
        if (Boolean.FALSE.equals(stored)) {
            response.setStatus(409);
            return false;
        }
        return true;
    }
}
