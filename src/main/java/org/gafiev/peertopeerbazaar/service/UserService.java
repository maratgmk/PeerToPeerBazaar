package org.gafiev.peertopeerbazaar.service;

import jakarta.validation.constraints.Email;
import org.gafiev.peertopeerbazaar.entity.user.Role;
import org.gafiev.peertopeerbazaar.entity.user.User;
import org.gafiev.peertopeerbazaar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<User> findBy(Long id) {
        return userRepository.findBy(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Set<User> getUserByRole(Role role){
        return userRepository.getUsersByRole(role);
    }

    @Transactional(readOnly = true)
    public Set<User> findAllByRatingSeller(Integer ratingSeller){
       return userRepository.findAllByRatingSeller(ratingSeller);
    }

    @Transactional
    public User createUser (User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser (Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User  not found with id " + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPhone(userDetails.getPhone());
        user.setRole(userDetails.getRole());
        user.setRatingSeller(userDetails.getRatingSeller());
        user.setRatingBuyer(userDetails.getRatingBuyer());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser (Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User  not found with id " + id));
        userRepository.delete(user);
    }

}
