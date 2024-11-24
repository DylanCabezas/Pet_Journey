package com.dbp.pet_journey.auth.utils;

import com.dbp.pet_journey.auth.domain.UserAccount;
import com.dbp.pet_journey.auth.domain.UserDetailsServiceImpl;
import com.dbp.pet_journey.auth.infraestructure.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationUtils {

    @Autowired
    private UserAccountRepository userAccountRepository;
}
