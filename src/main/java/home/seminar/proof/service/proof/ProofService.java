package home.seminar.proof.service.proof;

import home.seminar.proof.domain.ProofForm;

public interface ProofService {

	public void save(ProofForm proof);
	public ProofForm getProofById(Long id);
}
