package home.seminar.proof.service.proof;

import java.util.List;

import home.seminar.proof.domain.entity.Proof;
import home.seminar.proof.domain.form.ProofForm;

public interface ProofService {

	public Proof save(ProofForm proof);
	public ProofForm getProofById(Long id);
	Proof update(ProofForm proof);
	List<Proof> findAll();
	List<ProofForm> findAllRoot();
	void deleteProof(Long id);
	List<ProofForm> findByParentId(Long id);
	List<ProofForm> findByType(String type);
	List<Proof> findByTitle(String title);
}
