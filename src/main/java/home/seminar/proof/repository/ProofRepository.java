package home.seminar.proof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import home.seminar.proof.domain.Proof;

public interface ProofRepository extends JpaRepository<Proof, Long> {

}
