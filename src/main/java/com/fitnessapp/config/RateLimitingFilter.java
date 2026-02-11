package com.fitnessapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {
    private static final int MAX_REQUESTS_PER_MINUTE = 240;
    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String key = request.getRemoteAddr();
        Window window = windows.computeIfAbsent(key, k -> new Window(Instant.now().plusSeconds(60), 0));
        if (Instant.now().isAfter(window.resetAt())) {
            window = new Window(Instant.now().plusSeconds(60), 0);
            windows.put(key, window);
        }
        if (window.count() > MAX_REQUESTS_PER_MINUTE) {
            response.sendError(429, "Rate limit exceeded");
            return;
        }
        windows.put(key, new Window(window.resetAt(), window.count() + 1));
        filterChain.doFilter(request, response);
    }

    private record Window(Instant resetAt, int count) {}
}
