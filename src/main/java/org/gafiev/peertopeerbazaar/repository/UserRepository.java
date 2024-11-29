package org.gafiev.peertopeerbazaar.repository;

import lombok.NonNull;
import org.gafiev.peertopeerbazaar.entity.user.Role;
import org.gafiev.peertopeerbazaar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findBy(Long id);
    Optional<User> findByEmail(String email);
    Set<User> getUsersByRole(Role role);
    Set<User> findAllByRatingSeller(Integer ratingSeller);

}
