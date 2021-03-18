package com.joljak.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.joljak.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Autowired
    private UserDetailsService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() // .csrf() 이 설정은 CSRF 공격을 막기위해 세팅되어있는데 .disable()을 세팅해 놓지 않으면 해당 작업을 수행하기 위한 파라미터가 없다고 에러가 발생하기 때문에 .disable()을 해주도록 한다.
                // 토큰(jwt) 기반으로 rest api 서버 개발시에는 대체로 사용하지 않는것으로 나옴 // https://codeday.me/ko/qa/20190531/677751.html
                .authorizeRequests().antMatchers("/api/**").permitAll() // 인증처리는 JwtInterceptor 에서 처리한다. 향후에 spring-security 를 제대로 사용할 수 있도록 추가 작업을 하자.(지금도 별 문제는 아니라고 생각한다.)
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .addFilterBefore(new JwtAuthenticationFilter(authService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/**");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
