package com.friend.now.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    protected AuthenticationManager authenticationManager(final AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }


    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/", "/client/**", "/service/login").permitAll()
                                .requestMatchers("/manage/info", "/manage/health", "**(.csv|.xml)$").permitAll()
                                .requestMatchers("/service/public/**", "/service/metrics**").permitAll()
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .requestMatchers(HttpMethod.POST).permitAll()
                                .requestMatchers(HttpMethod.DELETE).permitAll()
                                .requestMatchers(HttpMethod.PUT).permitAll()
                                .requestMatchers(HttpMethod.PATCH).permitAll()
                                .requestMatchers("/service/admin/**", "/service/**/admin/**", "/manage/**").permitAll()
                                .requestMatchers("/service/**").permitAll()
                                .requestMatchers("/anonymous/**").permitAll()
                                .anyRequest().permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/client**", "/service/public");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin", "Content-Length", "Accept", "Authorization", "Origin", "Referer", "User-Agent"));
        configuration.setExposedHeaders(Arrays.asList("Error"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}