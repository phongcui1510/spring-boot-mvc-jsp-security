package home.seminar.proof.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.seminar.proof.domain.entity.User;
import home.seminar.proof.domain.enumeration.Role;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUsername(String username);
    List<User> findByRole(Role role);
}
