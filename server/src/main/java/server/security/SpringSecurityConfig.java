package server.security;

import server.security.jwt.JwtSecurityConfigurer;
import server.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/singin").permitAll()
                .antMatchers(HttpMethod.POST, "/routes/**", "/autos/**", "/personnels/**", "/journals/**")
                        .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/routes/**", "/autos/**", "/personnels/**", "/journals/**")
                        .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/routes/**", "/autos/**", "/personnels/**", "/journals/**")
                        .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/routes/**", "/autos/**", "/personnels/**", "/journals/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }
}
