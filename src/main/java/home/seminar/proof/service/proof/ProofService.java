package home.seminar.proof.service.proof;

import java.util.List;

import home.seminar.proof.domain.Proof;
import home.seminar.proof.domain.ProofForm;

public interface ProofService {

	public void save(ProofForm proof);
	public ProofForm getProofById(Long id);
	Proof update(ProofForm proof);
	List<Proof> findAll();
	List<Proof> findAllRoot();
	void deleteProof(Long id);
	List<Proof> findByParentId(Long id);
	List<Proof> findByType(String type);
}
