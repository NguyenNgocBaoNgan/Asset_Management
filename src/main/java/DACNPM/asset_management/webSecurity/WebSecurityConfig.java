package DACNPM.asset_management.webSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImp userDetailServiceImp;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // vô hiệu hóa tính năng bảo vệ CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**").permitAll().anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/dang-nhap")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home") // login success
                        .failureUrl("/dang-nhap?error=true") // login failed
                        .usernameParameter("email")    // username
                        .passwordParameter("password")) // password
                .logout(logout -> logout
                        .logoutUrl("/dang-xuat"));
//                        .logoutSuccessUrl("/")); // logout success
        return http.build();
    }
    // xác thực người dùng
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceImp);
        authProvider.setPasswordEncoder(passwordEncoderConfig.encoder());
        return authProvider;
    }
}


