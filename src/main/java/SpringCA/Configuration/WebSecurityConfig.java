package SpringCA.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().formLogin()//
                    .loginProcessingUrl("/admin/j_spring_security_login")//
                    .loginPage("/login_admin")//
                    .defaultSuccessUrl("/admin/admin")//
                    .failureUrl("/login_admin?message=error")//
                    .usernameParameter("username")//
                    .passwordParameter("password")
                    .and().exceptionHandling().accessDeniedPage("/403")
                    .and().logout().logoutUrl("/admin/j_spring_security_logout").logoutSuccessUrl("/login_admin?message=logout");
        }

    }


    @Configuration
    @Order(2)
    public class LecturerSecurityConfig extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/lecturer/**").authorizeRequests().antMatchers("/lecturer/**").hasRole("LECTURER").and().formLogin()//
                    .loginProcessingUrl("/lecturer/j_spring_security_login")//
                    .loginPage("/login_lecturer")//
                    .defaultSuccessUrl("/lecturer/lecturer")//
                    .failureUrl("/login_lecturer?message=error")//
                    .usernameParameter("username")//
                    .passwordParameter("password")
                    .and().exceptionHandling().accessDeniedPage("/403")
                    .and().logout().logoutUrl("/lecturer/j_spring_security_logout").logoutSuccessUrl("/login_lecturer?message=logout");
        }

    }


    @Configuration
    @Order(3)
    public class StudentSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/student/**").hasRole("STUDENT").and()
                    .formLogin()//
                    .loginProcessingUrl("/student/j_spring_security_login")//
                    .loginPage("/login_student")//
                    .defaultSuccessUrl("/student/student")//
                    .failureUrl("/login_student?message=error")//
                    .usernameParameter("username").passwordParameter("password")
                    .and().exceptionHandling().accessDeniedPage("/403")
                    .and().logout().logoutUrl("/student/j_spring_security_logout").logoutSuccessUrl("/login_student?message=logout");
        }
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
//
//
//    @Configuration
//    @Order(1)
//    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter{
//        public App1ConfigurationAdapter() {
//            super();
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception{
////            http.antMatcher("/admin*")
////                    .authorizeRequests()
////                    .anyRequest()
////                    .hasRole("ADMIN")
////                    .and()
////                    .formLogin()
////                    .loginPage("/loginAdmin")
////                    .loginProcessingUrl("/admin_login")
////                    .failureUrl("/loginAdmin?error=loginError")
////                    .defaultSuccessUrl("/adminPage")
////                    .and()
////                    .logout()
////                    .logoutUrl("/admin_logout")
////                    .logoutSuccessUrl("/protectedLinks")
////                    .deleteCookies("JSESSIONID")
////                    .and()
////                    .exceptionHandling()
////                    .accessDeniedPage("/403")
////                    .and()
////                    .csrf().disable();
//
//            http.antMatcher("/admin**").authorizeRequests().antMatchers("/admin**").hasRole("ADMIN").and().formLogin()//
//                    .loginProcessingUrl("/admin_login")//
//                    .loginPage("/loginAdmin")//
//                    .defaultSuccessUrl("/adminPage")//
//                    .failureUrl("/loginAdmin?message=error")//
//                    .usernameParameter("username")//
//                    .passwordParameter("password")
//                    .and().exceptionHandling().accessDeniedPage("/403")
//                    .and().logout().logoutUrl("/admin/j_spring_security_logout").logoutSuccessUrl("/login1?message=logout");
//        }
//    }
//
//    @Configuration
//    @Order(2)
//    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//        public App2ConfigurationAdapter() {
//            super();
//        }
//
//        protected void configure(HttpSecurity http) throws Exception {
//            http.antMatcher("/lecturer*")
//                    .authorizeRequests()
//                    .anyRequest()
//                    .hasRole("LECTURER")
//
//                    .and()
//                    .formLogin()
//                    .loginPage("/loginLecturer")
//                    .loginProcessingUrl("/lecturer_login")
//                    .failureUrl("/loginLecturer?error=loginError")
//                    .defaultSuccessUrl("/lecturerPage")
//
//                    .and()
//                    .logout()
//                    .logoutUrl("/lecturer_logout")
//                    .logoutSuccessUrl("/protectedLinks")
//                    .deleteCookies("JSESSIONID")
//
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedPage("/403")
//
//                    .and()
//                    .csrf().disable();
//        }
//    }
//
//    @Configuration
//    @Order(3)
//    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//        public App3ConfigurationAdapter() {
//            super();
//        }
//
//        protected void configure(HttpSecurity http) throws Exception {
//            http.antMatcher("/student*")
//                    .authorizeRequests()
//                    .anyRequest()
//                    .hasRole("STUDENT")
//
//                    .and()
//                    .formLogin()
//                    .loginPage("/loginStudent")
//                    .loginProcessingUrl("/login?role=student")
//                    .failureUrl("/loginStudent?error=loginError")
//                    .defaultSuccessUrl("/default")
//
//                    .and()
//                    .logout()
//                    .logoutUrl("/student_logout")
//                    .logoutSuccessUrl("/protectedLinks")
//                    .deleteCookies("JSESSIONID")
//
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedPage("/403")
//
//                    .and()
//                    .csrf().disable();
//        }
//    }
}
