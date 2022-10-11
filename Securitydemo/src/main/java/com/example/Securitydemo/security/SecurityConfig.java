package com.example.Securitydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bob").password(passwordEncoder().encode("111"))
                .roles("USER")
                .and()
                .withUser("alice").password(passwordEncoder().encode("111"))
                .roles("USER","EDITOR")
                .and()
                .withUser("ana").password(passwordEncoder().encode("111"))
                .roles("USER","EDITOR","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/profile").hasRole("USER")
                .antMatchers("/admin/blogs").hasAnyRole("EDITOR","ADMIN")
                .antMatchers("admin/users").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .antMatchers("/profile","/admin/**").authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login-process")
                    .defaultSuccessUrl("/",true)
                .usernameParameter("email")
                .passwordParameter("pass")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .permitAll()
                    .and()
                .httpBasic();
    }
}
