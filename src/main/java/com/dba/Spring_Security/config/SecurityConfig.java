package com.dba.Spring_Security.config;

import org.springframework.beans.factory.annotation.Autowired;
// import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
// import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
// import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    // @Autowired
    // private JwtFilter jwtFilter;

    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // no
        // password encoder for demo purpose only
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // * LAMBDA version
        http.csrf(customizer -> customizer.disable()) // disable csrf
                .authorizeHttpRequests(req -> req
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                // * enable auth for all requests except for the one that match the path
                // provided
                // .formLogin(Customizer.withDefaults()) // * add form for auth, without the
                // * browser present an alert to insert username and password
                // .httpBasic(Customizer.withDefaults()) // * add basic auth logic
                // * make session stateless
                // .sessionManagement(session ->
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                // * OAuth2 (instead of stateless session and jwt)-(**remove same site strict**)
                .oauth2Login(Customizer.withDefaults());

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

    /*
     * @Bean
     * UserDetailsService userDetailsService() {
     * UserDetails user = User
     * .withDefaultPasswordEncoder() // deprecated, use only for demos
     * .username("john")
     * .password("123456")
     * .roles("USER")
     * .build();
     * 
     * UserDetails admin = User
     * .withDefaultPasswordEncoder()
     * .username("admin")
     * .password("456789")
     * .roles("ADMIN")
     * .build();
     * 
     * return new InMemoryUserDetailsManager(user, admin);
     * }
     */

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
