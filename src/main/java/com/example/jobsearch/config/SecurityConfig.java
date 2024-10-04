package com.example.jobsearch.config;

import com.example.jobsearch.handler.MySimpleUrlAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
public final MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;
    private static final String USER_QUERY = "select email, password, enabled from USERS where email = ?;";
    private static final String AUTHORITIES_QUERY = "select email, account_type from USERS where email = ?;";
    private final DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/api/auth/login")

                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/")
                        .successHandler(myAuthenticationSuccessHandler())
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                                authorize

                                .requestMatchers("/users/forgot_password", "/users/reset_password").permitAll()
                                        .requestMatchers("/users/register").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                                        .requestMatchers("/vacancies/*/").permitAll()
                                        .requestMatchers("/resumes/**").hasAuthority("EMPLOYER")
                                        .requestMatchers("/resume/").hasAuthority("EMPLOYEE")
                                        .requestMatchers("/employee/**").hasAuthority("EMPLOYEE")
                                        .requestMatchers("/employer/**").hasAuthority("EMPLOYER")
                                        .requestMatchers(HttpMethod.POST, "/employee/**").hasAuthority("EMPLOYEE")

                                        .requestMatchers(HttpMethod.POST, "/employer/**").hasAuthority("EMPLOYER")
                                        .requestMatchers("/users/profile").fullyAuthenticated()
                                        .anyRequest().fullyAuthenticated()
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }



}
