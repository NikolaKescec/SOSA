package hr.fer.lab1.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${oauth2.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String issuer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .authorizeRequests()
            .antMatchers("/", "h2-console/**").permitAll()
            .antMatchers(HttpMethod.GET, "/students").hasAuthority("SCOPE_read:students")
            .antMatchers(HttpMethod.POST, "/students").hasAuthority("SCOPE_write:students")
            .antMatchers(HttpMethod.GET, "/records").hasAuthority("SCOPE_read:records")
            .antMatchers(HttpMethod.POST, "/records").hasAuthority("SCOPE_write:records")
            .and()
            .oauth2ResourceServer().jwt();

        // for h2-console, these two options are not important for resource servers anyhow
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        final NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        final OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        final OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        final OAuth2TokenValidator<Jwt> withAudience =
            new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

}
