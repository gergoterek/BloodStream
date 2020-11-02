package com.elte.BloodStream.security;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Donor> optionalDonor = donorRepository.findByUsername(username);
        if (optionalDonor.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Donor donor = optionalDonor.get();
        authenticatedUser.setDonor(donor);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(donor.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(donor.getUsername(), donor.getPassword(), grantedAuthorities);
    }
}
