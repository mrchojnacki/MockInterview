package pl.coderslab.App;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   /* @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .anonymous()
                .authorities("ROLE_ANONYMOUS")
                .and()
                .authorizeRequests()
                .antMatchers("/form").hasAnyRole("USER", "ADMIN")
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/")
                .permitAll();
        return http.build();
    }*/
}
