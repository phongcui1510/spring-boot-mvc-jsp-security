package home.seminar.proof.service.proof;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import home.seminar.proof.domain.entity.Proof;
import home.seminar.proof.domain.form.ProofForm;
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
		proof.setCreatedDate(new Date());
		proof.setParentId(form.getParentId());
		proof.setType(form.getType());
		repository.save(proof);
	}

	@Override
	public ProofForm getProofById(Long id) {
		ProofForm form = new ProofForm();
		Proof proof = repository.findOne(id);
		convertEntityToModel(form, proof);
		return form;
	}

	private void convertEntityToModel(ProofForm form, Proof proof) {
		form.setId(proof.getId());
		form.setTitle(proof.getTitle());
		form.setDescription(proof.getDescription());
		form.setStartDate(proof.getStartDate());
		form.setEndDate(proof.getEndDate());
		form.setFilePath(proof.getFilePath());
		form.setCreatedDate(proof.getCreatedDate());
		form.setCreatedBy(proof.getCreatedBy());
		form.setModifiedDate(proof.getModifiedDate());
		form.setModifiedBy(proof.getModifiedBy());
		form.setParentId(proof.getParentId());
		form.setType(proof.getType());
	}

	@Override
	public Proof update(ProofForm form) {
		Proof proof = repository.findOne(form.getId());
		if (!StringUtils.isEmpty(form.getTitle())) {
			proof.setTitle(form.getTitle());
		}
		if (!StringUtils.isEmpty(form.getDescription())) {
			proof.setDescription(form.getDescription());
		}
		if (!StringUtils.isEmpty(form.getStartDate())) {
			proof.setStartDate(form.getStartDate());
		}
		if (!StringUtils.isEmpty(form.getEndDate())) {
			proof.setEndDate(form.getEndDate());
		}
		if (!StringUtils.isEmpty(form.getFilePath())) {
			proof.setFilePath(form.getFilePath());
		}
		proof.setModifiedBy(form.getCreatedBy());
		proof.setModifiedDate(new Date());
		if (!StringUtils.isEmpty(form.getParentId())) {
			proof.setParentId(form.getParentId());
		}
		return repository.save(proof);
	}

	@Override
	public List<Proof> findAll() {
		return repository.findAll();
	}

	@Override
	public void deleteProof(Long id) {
		repository.delete(id);
	}

	@Override
	public List<Proof> findAllRoot() {
		return repository.findAllRoot();
	}

	@Override
	public List<Proof> findByParentId(Long id) {
		return repository.findByParentIdOrderByTypeAsc(id);
	}

	@Override
	public List<Proof> findByType(String type) {
		return repository.findByType(type);
	}

	@Override
	public List<Proof> findByTitle(String title) {
		return repository.findByTitleLikeIgnoreCase("%"+title+"%");
	}
}
