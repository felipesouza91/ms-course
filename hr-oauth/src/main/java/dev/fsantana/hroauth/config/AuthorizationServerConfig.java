package dev.fsantana.hroauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final JwtTokenStore jwtTokenStore;
    private final AuthenticationManager authenticationManager;
    private final String clientName;
    private final String clientSecret;

    public AuthorizationServerConfig(
            BCryptPasswordEncoder passwordEncoder,
            JwtAccessTokenConverter jwtAccessTokenConverter,
            JwtTokenStore jwtTokenStore,
            AuthenticationManager authenticationManager,
            @Value("${oauth.client.name}") String clientName,
            @Value("${oauth.client.secret}") String clientSecret) {
        this.passwordEncoder = passwordEncoder;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenStore = jwtTokenStore;
        this.authenticationManager = authenticationManager;
        this.clientName = clientName;
        this.clientSecret = clientSecret;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientName)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("READ", "WRITE")
                .authorizedGrantTypes("password")
                        .accessTokenValiditySeconds(60*60*24);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter);
    }
}
