package com.valtech.team18.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.valtech.team18.service.UserDetailServiceImpl;

 


 


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Lazy
   @Autowired
   UserDetailServiceImpl userDetailService;

 

//   @Bean
//   public BCryptPasswordEncoder passwordEncoder() {
//      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//      return bCryptPasswordEncoder;
//   }

 

   @Override
   protected void configure(HttpSecurity http) throws Exception {

 

      http.csrf().disable();

 

      // Requires login with role ROLE_EMPLOYEE or ROLE_MANAGER.
      // If not, it will redirect to /admin/login.
      http.authorizeRequests().antMatchers("/admin/adminHome", "/admin/orderDetails", "/admin/newOrder","/admin/truckDetails", "/admin/supplierDetails", "/admin/supplierApproval", "/admin/driverApproval", "/supplier/supplierHome/{id}", "/truckDriver/truckDriverHome/{id}")//
            .access("hasAnyRole('ADMIN')");

      
      http.authorizeRequests().antMatchers("/supplier/supplierHome/{id}", "/newPassword/supp/{id}")
      .access("hasAnyRole('SUPPLIER')");
      

      http.authorizeRequests().antMatchers("/truckDriver/truckDriverHome/{id}", "/newPassword/driver/{id}")
      .access("hasAnyRole('TRUCKDRIVER')");

      
      
//      http.authorizeRequests().antMatchers("/forgotPassword","/aboutUs","/contactUs")
//      .access("hasAnyRole('TRUCKDRIVER'), hasAnyRole('ADMIN'), hasAnyRole('SUPPLIER')");

 

      // Pages only for MANAGER
//      http.authorizeRequests().antMatchers("/admin/product").access("hasRole('ROLE_MANAGER')");

 

      // When user login, role XX.
      // But access to the page requires the YY role,
      // An AccessDeniedException will be thrown.
      http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

 

      // Configuration for Login Form.
      http.authorizeRequests().and().formLogin()//

 

            //
            .loginProcessingUrl("/j_spring_security_check") // Submit URL
            .loginPage("/")//
//            .defaultSuccessUrl("/admin/accountInfo")//
            .failureUrl("/")//
            .usernameParameter("email")//
            .passwordParameter("password")

 

            // Configuration for the Logout page.
            // (After logout, go to home page)
            .and().logout().logoutUrl("/admin/adminHome").logoutSuccessUrl("/");

 

   }
}
