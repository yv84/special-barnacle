package me.yv84.specialbarnacle.fuzzypancake.web.config.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;

    
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    
    @Value("${app.jwt_token_rca_public_key}")
    private String jwtTokenRcaPublicKey;

    
    /**
     * curl -i --header "Accept:application/json" -X GET -b 'C:\Temp\1\cookies.txt' http://localhost:8899/sbarnacle-studious-spoon/ping/ping
     */
    @Override
    public void configure(final HttpSecurity http) throws Exception { 
        // @formatter:off
        http
            .cors()
        .and()
            .csrf().disable()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
            .httpBasic()
        .and().authorizeRequests()
            .antMatchers("/metrics").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/ping/ping").permitAll()
            .antMatchers(HttpMethod.POST,"/**").authenticated()
            .antMatchers(HttpMethod.PUT,"/**").authenticated()
            .antMatchers(HttpMethod.DELETE,"/**").authenticated()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .anyRequest().authenticated()
        .and()
            .httpBasic();
        // @formatter:on                
    }

    
    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
         final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
         converter.setAccessTokenConverter(customAccessTokenConverter);
         converter.setVerifierKey(jwtTokenRcaPublicKey);
         converter.setJwtClaimsSetVerifier(customJwtClaimVerifier());
         return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }


    @Bean
    public JwtClaimsSetVerifier customJwtClaimVerifier() {
        return new CustomClaimVerifier();
    }

}
