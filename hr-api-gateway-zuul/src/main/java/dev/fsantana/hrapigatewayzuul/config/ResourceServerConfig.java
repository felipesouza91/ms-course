package dev.fsantana.hrapigatewayzuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String[] PUBLIC = {"/hr-oauth/oauth/token"};
    private final String[] OPERATOR = {"/hr-worker/**"};
    private final String[] ADMIN = {"/hr-payroll/**", "/hr-user/**"};

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
    }
}
