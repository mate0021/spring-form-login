package rnd.mate00.springformlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .successForwardUrl("/restricted")
                .failureHandler(customAuthFailedHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("user1").password(passwordEncoder().encode("pass1")).roles("ADMIN");
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CustomAuthenticationFailedHandler customAuthFailedHandler() {
        return new CustomAuthenticationFailedHandler();
    }
}
