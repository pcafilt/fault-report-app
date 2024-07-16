package gr.aueb.cf.project8.service;

import gr.aueb.cf.project8.model.User;
import gr.aueb.cf.project8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Optional<User> authenticate(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
            return user;
        }
        return null;
    }

    public Optional<User> findByUsername(String username) {
      return  userRepository.findByUsername(username);
    }
}
