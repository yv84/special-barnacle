package me.yv84.specialbarnacle.authumbrella.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${myapp.ldap.url}")
    private String ldapUrl;

	@Value("${myapp.ldap.ldif}")
    private String ldapLdif;

	@Value("${myapp.ldap.user-dn-patterns}")
	  private String ldapUserDnPatterns;

	@Value("${myapp.ldap.manager-dn-patterns}")
    private String ldapManagerDnPatterns;

	@Value("${myapp.ldap.manager-password}")
	   private String ldapManagerPassword;

	@Value("${myapp.ldap.user-search-base}")
    private String ldapUserSearchBase;

	@Value("${myapp.ldap.group-search-base}")
    private String ldapGroupSearchBase;

	@Value("${myapp.ldap.group-search-filter}")
    private String ldapGroupSearchFilter;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
			.userDnPatterns(ldapUserDnPatterns)
			.groupSearchBase(ldapGroupSearchBase)
			.contextSource()
				// .passwordCompare().passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("userPassword")
				.url(ldapUrl)
					.managerDn(ldapManagerDnPatterns)
					.managerPassword(ldapManagerPassword);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.cors().and()
                .csrf().disable()
	        .authorizeRequests()
	            .antMatchers("/oauth/**").permitAll()
	            .antMatchers("/api/**").permitAll()
	            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	            .anyRequest().fullyAuthenticated()
	        .and()
	            .httpBasic()
	        .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
