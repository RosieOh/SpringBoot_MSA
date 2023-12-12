package com.member2.config;

import com.member2.service.CustomUserDetailsService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.io.InputStream;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("-------------------  filter Chain  ------------------");

        http
            .authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                    .requestMatchers("/", "/**","/login","/join").permitAll()
                    .anyRequest().authenticated());

        http
            .formLogin((formLogin) ->
                formLogin.loginPage("/login")
            );

        http
            .csrf((csrf) ->
                csrf.disable()
            );

        http
            .logout((logout) ->
                logout.logoutUrl("/logout").logoutSuccessUrl("/")
            );

        http
            .exceptionHandling((exceptionHandling) ->
                exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("-------------------- WebSecurity ----------------------");
        return (web) -> web.ignoring().requestMatchers(
                PathRequest.toStaticResources().atCommonLocations());
    }
}