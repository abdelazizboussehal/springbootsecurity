package com.example.demo.security;

import com.example.demo.filters.FilerAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityTest extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    //my custom filter
    @Autowired
    FilerAuth jwtRequestFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
        /*auth.inMemoryAuthentication()
                .withUser("aziz")
                .password("aziz")
                .roles("USER")
                .and()
                .withUser("foo")
                .password("foufa")
                .roles("ADMIN")*/
        ;
    }
    //for authenticate manager in root
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder hashPassword(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/report/**").permitAll()
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .and().formLogin();*/

        http.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Disable session for jwt
        // Add costume filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
