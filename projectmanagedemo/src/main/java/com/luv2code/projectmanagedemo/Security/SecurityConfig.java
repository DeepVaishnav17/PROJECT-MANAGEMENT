package com.luv2code.projectmanagedemo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {



    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);


        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, true as enabled from db_project_manage.user where username = ? ");


        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select u.username, r.role_name as role from db_project_manage.user u join db_project_manage.role r on u.id = r.user_id where u.username=? ");


//        jdbcUserDetailsManager.setRolePrefix("");
        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer

                        //FOR ROLE DELETE OR POST
                        .requestMatchers(HttpMethod.POST,"/api/users/**").hasRole("admin")

                        //FOR USERS
                        .requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("admin", "employee", "manager")
                        .requestMatchers(HttpMethod.GET,"/api/users").hasRole("admin")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("admin")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("admin")

                        //FOR DEPARTMENTS
                        .requestMatchers(HttpMethod.GET,"/api/departments/**").hasAnyRole("admin", "manager", "employee")
                        .requestMatchers(HttpMethod.POST, "/api/departments").hasAnyRole("admin", "manager")
                        .requestMatchers(HttpMethod.PUT, "/api/departments/**").hasRole("admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/departments/**").hasRole("admin")

                        //FOR PROJECTS
                        .requestMatchers(HttpMethod.GET,"/api/projects/**").hasAnyRole("admin", "manager", "employee")
                        .requestMatchers(HttpMethod.POST, "/api/projects").hasAnyRole("admin", "manager")
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasRole("admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/**").hasRole("admin")

                        //FOR ROLES
                        .requestMatchers(HttpMethod.GET, "/api/roles/**").hasAnyRole("employee", "manager", "admin")
                        .requestMatchers(HttpMethod.POST, "/api/projects").hasAnyRole("manager", "admin")
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasAnyRole("manager", "admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/roles/**").hasAnyRole("admin")

        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());

        return http.build();


    }


}


//admin = admin@123
// employee = employee@123
// manager = manager@123
