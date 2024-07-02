package DACNPM.asset_management.webSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImp implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!email.equals("user")) throw new UsernameNotFoundException(email + " không tồn tại trong database");
        UserDetails userDetails = User.withUsername(email).password("$2a$10$4qvPvs1d0YTt9TnUfpbfwudsPCqLu54SoRZdj8IAey0bthFurf88y").build();
        return userDetails;
    }
}