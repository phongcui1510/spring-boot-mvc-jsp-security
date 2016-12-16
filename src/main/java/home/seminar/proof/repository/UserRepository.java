package home.seminar.proof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.seminar.proof.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUsername(String username);
}
