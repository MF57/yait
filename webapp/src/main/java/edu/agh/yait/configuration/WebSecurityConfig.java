package edu.agh.yait.configuration;

import edu.agh.yait.security.CustomAuthenticationProvider;
import edu.agh.yait.security.JWTAuthenticationFilter;
import edu.agh.yait.security.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("edu.agh.yait")

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String API_URL = "/api/v1";

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, API_URL + "/login").permitAll()
                .anyRequest().authenticated()
                .and()

                // filter the api/login requests
                .addFilterBefore(new JWTLoginFilter(API_URL + "/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/build/**");
        web.ignoring().antMatchers("/node_modules/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/index.html");
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/v1/issues");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
