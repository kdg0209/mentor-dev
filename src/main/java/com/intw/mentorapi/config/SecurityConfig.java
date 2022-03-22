package com.intw.mentorapi.config;

import com.intw.mentorapi.handler.AuthenticationEntryPointHandler;
import com.intw.mentorapi.handler.JwtAuthenticationFilter;
import com.intw.mentorapi.handler.JwtProvider;
import com.intw.mentorapi.handler.WebAccessDeniedHandler;
import com.intw.mentorapi.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final WebAccessDeniedHandler webAccessDeniedHandler;
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {

        // swagger
        web.ignoring().antMatchers(
                "/v2/api-docs",  "/configuration/ui",
                "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**","/swagger/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/admin/**/**").hasRole("ADMIN")
                .antMatchers("/manager/**/**").hasRole("MANAGER")
//                .antMatchers(HttpMethod.POST, "/app/board/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/app/user/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/app/**/write").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/app/user/**").hasRole("USER")
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/app/auth/**").permitAll()
                .anyRequest().permitAll()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler)
                .accessDeniedHandler(webAccessDeniedHandler)
            .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
