package home.seminar.proof.service.proof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.seminar.proof.domain.Proof;
import home.seminar.proof.domain.ProofForm;
import home.seminar.proof.repository.ProofRepository;

@Service
public class ProofServiceImpl implements ProofService {

	@Autowired
	private ProofRepository repository;
	
	@Override
	public void save(ProofForm form) {
		Proof proof = new Proof();
		proof.setTitle(form.getTitle());
		proof.setDescription(form.getDescription());
		proof.setStartDate(form.getStartDate());
		proof.setEndDate(form.getEndDate());
		proof.setFilePath(form.getFilePath());
		proof.setCreatedBy(form.getCreatedBy());
		repository.save(proof);
	}

	@Override
	public ProofForm getProofById(Long id) {
		ProofForm form = new ProofForm();
		Proof proof = repository.findOne(id);
		form.setId(proof.getId());
		form.setTitle(proof.getTitle());
		form.setDescription(proof.getDescription());
		form.setStartDate(proof.getStartDate());
		form.setEndDate(proof.getEndDate());
		form.setFilePath(proof.getFilePath());
		return form;
	}
}
