package com.excilys.formation.cdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.excilys.formation.cdb.service.UserService;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	private final  UserService userService;

	@Autowired
	public SpringSecurityConfig(final UserService userService) {
		this.userService = userService;
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/", "/dashboard/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/", "/dashboard/**").hasRole("ADMIN")
		.antMatchers("/addComputer/**").hasRole("ADMIN")
		.antMatchers("/editComputer/**").hasRole("ADMIN")
		.and()
		.formLogin()
		//         .and()
		//         .httpBasic()
		.and()
		.csrf()
		.disable()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
		.logoutSuccessUrl("/dashboard")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID");

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println(new BCryptPasswordEncoder().encode("password"));
		return new BCryptPasswordEncoder();

	}

	/*@Bean
	public DigestAuthenticationEntryPoint digestEntryPoint() {

		final DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
		entryPoint.setRealmName("ENTRYPOINT CDB");
		entryPoint.setKey("keyCDB++");
		entryPoint.setNonceValiditySeconds(1);

		return entryPoint;
	}

	@Bean
	public DigestAuthenticationFilter digestAuthFilter(final DigestAuthenticationEntryPoint entryPoint) {

		final DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
		filter.setUserDetailsService(userService);
		filter.setAuthenticationEntryPoint(entryPoint);

		return filter;
	}*/

}
