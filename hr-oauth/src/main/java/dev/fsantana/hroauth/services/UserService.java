package dev.fsantana.hroauth.services;

import dev.fsantana.hroauth.entities.User;
import dev.fsantana.hroauth.feign.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ResponseEntity<User> byEmail = userClient.getByEmail(email);
        if(byEmail.getBody() == null) {
            logger.error("Email not found", email);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email  found", email);

        return byEmail.getBody() ;
    }
}
