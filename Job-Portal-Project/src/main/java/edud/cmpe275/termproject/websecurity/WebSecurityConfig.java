//package edud.cmpe275.termproject.websecurity;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import edu.cmpe275.termproject.service.JobSeekerService;
//
//@Configuration
//@EnableWebSecurity
////@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
////	@Bean
////	public BCryptPasswordEncoder bCryptPasswordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////	
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		System.out.println("Inside configure Websecurity");
////		http.authorizeRequests().anyRequest().permitAll();
////		//.antMatchers("/**").permitAll();
////		//,"/positions/**","/company/**", "/jobseeker/**"
////		
////	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers("/**");
//	}
//	
//		
//	 @Override
//	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth
//	                .userDetailsService(userDetailsService)
//	                .passwordEncoder(new BCryptPasswordEncoder());
//	    }
//	
//
//
//}
