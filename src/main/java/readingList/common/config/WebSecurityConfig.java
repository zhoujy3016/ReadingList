package readingList.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import readingList.common.component.LoginSuccessHandler;
import readingList.service.CustomUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public static NoOpPasswordEncoder passwordEncoder() { // 不加密，官方不推荐
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

	@Bean
	UserDetailsService customerUserService() {
		return new CustomUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers( "/static/**", "/js/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.permitAll()
				.successHandler(loginSuccessHandler())
			.and()
			.logout()
				.permitAll();
//				.and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**", "/js/**");
	}


	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

}
