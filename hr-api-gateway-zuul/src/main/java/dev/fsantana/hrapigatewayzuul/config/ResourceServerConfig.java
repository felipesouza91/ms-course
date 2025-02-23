package dev.fsantana.hrapigatewayzuul.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String[] PUBLIC = {"/hr-oauth/oauth/token"};
    private final String[] OPERATOR = {"/hr-worker/**"};
    private final String[] ADMIN = {
            "/hr-payroll/**",
            "/hr-user/**",
            "/actuator/**",
            "/hr-worker/actuator/**",
            "/hr-user/actuator/**",
            "/hr-payroll/actuator/**"};

    private final JwtTokenStore tokenStore;

    public ResourceServerConfig(JwtTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.formLogin().disable()
               .csrf().disable()
               .authorizeRequests()
               .antMatchers(PUBLIC).permitAll()
               .antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN")
               .antMatchers(ADMIN).hasAnyRole("ADMIN")
               .anyRequest().authenticated();

       http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConf = new CorsConfiguration();
        corsConf.setAllowedOrigins(List.of("*"));
        corsConf.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "PATCH"));
        corsConf.setAllowCredentials(true);
        corsConf.setAllowedHeaders(List.of("Authorization", "Content-type") );
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConf);
        return corsConfigurationSource;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> filterRegistrationBean(){
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource())
        );
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
