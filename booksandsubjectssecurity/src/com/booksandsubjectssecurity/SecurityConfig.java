package com.booksandsubjectssecurity;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() 
	{
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();

	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().dataSource(dataSource);
		
	}
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().successForwardUrl("/login/optionRedirect.html").and().logout().logoutSuccessUrl("/login").and().httpBasic().and().csrf().disable();
	}
	
	 @Bean
	 public DataSource dataSource()
	 {
		    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/booksandsubjects");
	        dataSource.setUsername("root");
	        dataSource.setPassword("pass@word1");
	        return dataSource;
	 }
	

}
