package com.auth.tokenserver.config;

import com.auth.tokenserver.service.CustomAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {
    private static final String REALM="CRM_REALM";
    private static final int ONE_DAY = 60 * 60 * 24;
    private static final int THIRTHY_DAYS = 60 * 60 * 24 * 30;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private CustomAuthService customAuthService;

    @Autowired
    private PasswordEncoder encoder;

    /*@Autowired
    private RedisConnectionFactory redisConnectionFactory;*/


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("cli1")
                .secret(encoder.encode("sec1"))
                .authorizedGrantTypes("password", "refresh_token", "client_credentials")
                .authorities("ROLE")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(300)
                .refreshTokenValiditySeconds(THIRTHY_DAYS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(customAuthService);
    }

    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(encoder)
        .allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
        //return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
