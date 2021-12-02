package rnd.mate00.springformlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import rnd.mate00.springformlogin.handler.CustomAuthSuccessHandler;
import rnd.mate00.springformlogin.handler.CustomAuthenticationFailedHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/restricted").authenticated()
            .and()
            .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/handleLogin")
//                .defaultSuccessUrl("/restricted") // <- can be also done by authSuccessHandler
//                .failureUrl("/"); // <- can be also done by customAuthFailedHandler
                .successHandler(authSuccessHandler())
                .failureHandler(customAuthFailedHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("u1").password(passwordEncoder().encode("p1")).roles("ADMIN");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationFailureHandler customAuthFailedHandler() {
        return new CustomAuthenticationFailedHandler();
    }

    @Bean
    AuthenticationSuccessHandler authSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }
}
