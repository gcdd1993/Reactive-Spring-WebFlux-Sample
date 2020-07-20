package io.github.gcdd1993.reactive.sample.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @author Gcdd1993
 */
public class TokenFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var user = new User("user", "password", Collections.singletonList(new SimpleGrantedAuthority("admin")));
        var authentication = new UsernamePasswordAuthenticationToken(
                user,
                user,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        return chain.filter(exchange);
    }
}
