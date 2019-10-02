package ch.duartemendes.cronus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ch.duartemendes.cronus.domain.Role;
import ch.duartemendes.cronus.domain.User;
import ch.duartemendes.cronus.service.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers(SecurityConstants.AUTH_LOGIN_URL).permitAll()
				.antMatchers("/signIn").permitAll()
				.antMatchers("/cronus.html").permitAll()
				.antMatchers("/cronus-api.js").permitAll()
				.antMatchers("/cronus-view.js").permitAll()
				.antMatchers("/style.css").permitAll()
				.anyRequest().authenticated().and()
				.addFilter(new JwtAuthenticationFilter(authenticationManager(), userService))
				.addFilter(new JwtAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		for (User user : userService.findAll()) {
			for (Role role : user.getRoles()) {
				auth.inMemoryAuthentication().withUser(user.getUsername()).password(passwordEncoder().encode(user.getPassword()))
						.authorities(role.getName());

				auth.inMemoryAuthentication().withUser(user.getEmail()).password(passwordEncoder().encode(user.getPassword()))
						.authorities(role.getName());
			}
		}
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

		return source;
	}
}
