package ie.mohammed.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig_API_DB {
	// https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#multiple-httpsecurity
	@Configuration
	@Order(1)	// First to be checked....
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.antMatcher("/api/**")
			.authorizeRequests()
				.anyRequest().hasRole("API")
			.and()
				.httpBasic();
		}

		@Autowired
		DataSource dataSource;
				
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select myApiUser.userEmail, myApiUser.userPassword, myApiUser.userEnabled from myApiUser where myApiUser.userEmail=?")
			.authoritiesByUsernameQuery("SELECT role.userEmail, role.roleDescription FROM role WHERE role.userEmail=?");
		}
	}

	@Configuration
	public class FormLoginSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter  {
		@Autowired
		DataSource dataSource;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/css/**", "/","/index", "/jobs/**", "/bids/**","/newregisteruser/**", "/job/**", "/bid/**", "/h2/**").permitAll()
				.antMatchers("/newjob").hasRole("ADMIN")
				.antMatchers("/actuator/**").hasRole("ADMIN")
				.antMatchers("/newbid").hasAnyRole("ADMIN", "USER") 
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll()
					.usernameParameter("email")
		    .and()
		    	.httpBasic()
			.and()
				.logout()
					.logoutSuccessUrl("/index")
						.permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/403");
			
			http.csrf().disable();	// for h2 console
			http.headers().frameOptions().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT myUser.userEmail, myUser.userPassword, myUser.userEnabled FROM myUser WHERE myUser.userEmail=?")
			.authoritiesByUsernameQuery("SELECT role.userEmail, role.roleDescription FROM role WHERE role.userEmail=?");
		}
	}
}
