//package gr.aueb.cf.project8.authentication;
//
//import gr.aueb.cf.project8.model.User;
//import gr.aueb.cf.project8.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        System.out.println(user.getId());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
//    }
//}
//







package gr.aueb.cf.project8.authentication;

import gr.aueb.cf.project8.model.User;
import gr.aueb.cf.project8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User foundUser = user.get();
            System.out.println(foundUser.getId());
            return new org.springframework.security.core.userdetails.User(
                    foundUser.getUsername(),"{noop}"+
                    foundUser.getPassword(),
                    Collections.emptyList()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
