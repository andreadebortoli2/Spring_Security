package com.dba.Spring_Security.config;

// import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
// import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
// import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // * LAMBDA version
        http.csrf(customizer -> customizer.disable()) // disable csrf
                .authorizeHttpRequests(req -> req.anyRequest().authenticated()) // enable auth for all requests
                .formLogin(Customizer.withDefaults()) // add form for auth
                .httpBasic(Customizer.withDefaults()) // add basic auth logic
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // make session stateless

        // * extended version
        /*
         * Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new
         * Customizer<CsrfConfigurer<HttpSecurity>>() {
         * 
         * @Override
         * public void customize(CsrfConfigurer<HttpSecurity> t) {
         * t.disable();
         * }
         * };
         * http.csrf(custCsrf);
         * 
         * Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
         * AuthorizationManagerRequestMatcherRegistry> custHttp = new
         * Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.
         * AuthorizationManagerRequestMatcherRegistry>() {
         * 
         * @Override
         * public void customize(
         * AuthorizeHttpRequestsConfigurer<HttpSecurity>.
         * AuthorizationManagerRequestMatcherRegistry t) {
         * t.anyRequest().authenticated();
         * }
         * };
         * http.authorizeHttpRequests(custHttp);
         * 
         * Customizer<SessionManagementConfigurer<HttpSecurity>> custSession = new
         * Customizer<SessionManagementConfigurer<HttpSecurity>>() {
         * 
         * @Override
         * public void customize(SessionManagementConfigurer<HttpSecurity> t) {
         * t.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         * }
         * };
         * http.sessionManagement(custSession);
         */

        return http.build();
    }
}
