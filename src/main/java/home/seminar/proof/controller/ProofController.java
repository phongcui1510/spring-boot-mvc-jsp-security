package home.seminar.proof.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import home.seminar.proof.domain.CurrentUser;
import home.seminar.proof.domain.entity.Proof;
import home.seminar.proof.domain.form.ProofForm;
import home.seminar.proof.service.proof.ProofService;

@Controller
public class ProofController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofController.class);
	
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

	@Value("${uploadLocation}")
	private String uploadLocation;
	
	@Autowired
	private ProofService proofService;
	
	@InitBinder("form")
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
    @RequestMapping(value = "/proof/create", method = RequestMethod.GET)
	public ModelAndView createProof(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=false, name="parentId") Long parentId, @RequestParam(required=false, name="type") String type) {
    	LOGGER.info("Redirect to proof creation form");
    	ProofForm form = new ProofForm();
    	form.setAction(request.getContextPath()+"/proof/create");
    	form.setHeader("THÊM MINH CHỨNG");
    	List<ProofForm> proofs = proofService.findByType("BRANCH");
    	form.setProofs(proofs);
    	form.setParentId(parentId);
    	form.setType(type);
        return new ModelAndView("proof_create", "proof", form);
	}
    
    @RequestMapping(value = "/proof/create", method = RequestMethod.POST)
	public @ResponseBody String submitCreateProof(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") ProofForm form, Principal principal) throws IOException {
    	LOGGER.info("Processing proof");
    	if (form.getFile() != null && !StringUtils.isEmpty(form.getFile().getOriginalFilename())) {
    		String fullName = form.getFile().getOriginalFilename();
    		String name = fullName.substring(0, fullName.length() - 4);
    		String ext = fullName.substring(fullName.length() - 3, fullName.length());
    		Path filePath = Paths.get(uploadLocation).resolve(fullName);
    		try {
    			InputStream in = form.getFile().getInputStream();
    			File fileToSave = filePath.toFile();
    			if (fileToSave.exists()) {
    				int i = 1;
    				while(fileToSave.exists()) {
    					String temp = name + "-" + i;
    					i++;
    					fullName = temp+"."+ext;
    					fileToSave = Paths.get(uploadLocation).resolve(fullName).toFile();
    				}
    			}
    			Files.copy(in, filePath);
    			in.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		form.setFilePath(filePath.toString());
    	}
    	form.setCreatedBy(principal.getName());
//    	if (!StringUtils.isEmpty(form.getDescription())) {
//	    	String decode = decodeUTF8(form.getDescription().getBytes());
//			form.setDescription(decode);
//    	}
//		String decodeTitle = decodeUTF8(form.getTitle().getBytes());
//		form.setTitle(decodeTitle);
    	Proof savedProof = proofService.save(form);
    	if (form.getType().equalsIgnoreCase("LEAF")) {
    		response.sendRedirect(request.getContextPath()+"/proof/list");
    	}
        return savedProof.getId().toString();
	}
    
    @RequestMapping(value="/proof/download", method= RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getAttachment (@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	ProofForm form = proofService.getProofById(id);
		File file = new File(form.getFilePath());
		FileInputStream is = new FileInputStream(file);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
		IOUtils.copy(is, response.getOutputStream());
	}
   
    @RequestMapping(value="/proof/attachment/view", method= RequestMethod.GET)
	public void viewAttachment (@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
   	ProofForm form = proofService.getProofById(id);
		File file = new File(form.getFilePath());
		FileInputStream is = new FileInputStream(file);
		if (file.getName().contains(".pdf")) {
			response.setContentType("application/pdf");
		}
		response.setHeader("Content-Disposition", "inline; filename="+file.getName());
		IOUtils.copy(is, response.getOutputStream());
	}
    
    @RequestMapping(value = "/proof/view", method = RequestMethod.GET)
	public ModelAndView viewProof(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) {
    	ProofForm form = proofService.getProofById(id);
    	if (form.getType().equalsIgnoreCase("BRANCH")) {
    		List<ProofForm> proofs = proofService.findByParentId(form.getId());
    		List<ProofForm> proofForm = new ArrayList<ProofForm>();
    		for (ProofForm p : proofs) {
    			ProofForm pf = new ProofForm();
    			BeanUtils.copyProperties(p, pf);
    			List<ProofForm> children = proofService.findByParentId(pf.getId());
        		pf.setProofs(children);
        		proofForm.add(pf);
    		}
    		return new ModelAndView("proof_list", "proofs", proofForm);
    	}
    	return new ModelAndView("proof_view", "proof", form);
    }
    
    @RequestMapping(value = "/proof/list", method = RequestMethod.GET)
	public ModelAndView listProof(HttpServletRequest request, HttpServletResponse response, Principal principal) {
    	CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	LOGGER.info("Current User: " + currentUser.getUsername());
    	List<ProofForm> proofs = proofService.findAllRoot();
    	findChildren(proofs);
    	return new ModelAndView("proof_list", "proofs", proofs);
    }
    
    private void findChildren (List<ProofForm> lst) {
    	for (ProofForm p : lst) {
    		if (p.getType().equalsIgnoreCase("BRANCH")) {
    			List<ProofForm> children = proofService.findByParentId(p.getId());
    			p.setProofs(children);
    			findChildren(children);
    		}
    	}
    }
    
    @RequestMapping(value = "/proof/search", method = RequestMethod.GET)
	public String redirectToSearchProofPage(HttpServletRequest request, HttpServletResponse response) {
    	return "proof_search";
    }
    
    @RequestMapping(value = "/proof/submitSearch", method = RequestMethod.POST)
	public ModelAndView searchProof(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id, @RequestParam("title") String title) {
    	if (id !=null && !id.equals(0)) {
    		List<ProofForm> proofs = new ArrayList<ProofForm>();
    		ProofForm proof = proofService.getProofById(id);
    		proofs.add(proof);
    		return new ModelAndView("proof_search_result", "proofs", proofs);
    	} else if (!StringUtils.isEmpty(title)) {
    		String titleDecode = decodeUTF8(title.getBytes());
    		List<Proof> proofs = proofService.findByTitle(titleDecode);
    		return new ModelAndView("proof_search_result", "proofs", proofs);
    	}
    	return null;
    }
    
    @RequestMapping(value = "/proof/edit", method = RequestMethod.GET)
	public ModelAndView redirectEditProof(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) {
    	ProofForm form = proofService.getProofById(id);
    	form.setAction(request.getContextPath()+"/proof/edit");
    	form.setHeader("CHỈNH SỬA MINH CHỨNG");
    	List<ProofForm> proofs = proofService.findByType("BRANCH");
    	form.setProofs(proofs);
    	return new ModelAndView("proof_create", "proof", form);
    }
    
    @RequestMapping(value = "/proof/delete", method = RequestMethod.GET)
	public void deleteditProof(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) {
    	proofService.deleteProof(id);
    }
    
    @RequestMapping(value = "/proof/edit", method = RequestMethod.POST)
	public String editProof(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") ProofForm form) {
    	try {
//    		if (!StringUtils.isEmpty(form.getDescription())) {
//    			String decode = decodeUTF8(form.getDescription().getBytes());
//    			form.setDescription(decode);
//    		}
//    		if (!StringUtils.isEmpty(form.getTitle())) {
//    			String decodeTitle = decodeUTF8(form.getTitle().getBytes());
//    			form.setTitle(decodeTitle);
//    		}
    		proofService.update(form);
    	} catch (Exception e) {
    		LOGGER.error("Error when updating proof", e);
    	}
    	return "redirect:/home";
    }
    
    String decodeUTF8(byte[] bytes) {
	    return new String(bytes, UTF8_CHARSET);
	}

	byte[] encodeUTF8(String string) {
	    return string.getBytes(UTF8_CHARSET);
	}
}
