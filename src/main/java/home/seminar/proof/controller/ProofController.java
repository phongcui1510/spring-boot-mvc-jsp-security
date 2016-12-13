package home.seminar.proof.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import home.seminar.proof.domain.ProofForm;
import home.seminar.proof.service.proof.ProofService;

@Controller
public class ProofController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofController.class);

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
	public ModelAndView createProof(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("Redirect to proof creation form");
        return new ModelAndView("proof_create", "form", new ProofForm());
	}
    
    @RequestMapping(value = "/proof/create", method = RequestMethod.POST)
	public String submitCreateProof(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") ProofForm form, Principal principal) {
    	LOGGER.info("Processing proof");
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
    	form.setCreatedBy(principal.getName());
    	proofService.save(form);
        return "redirect:/home";
	}
    
   @RequestMapping(value="/proof/download", method= RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getThumbnail (@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	ProofForm form = proofService.getProofById(id);
		File file = new File(form.getFilePath());
		FileInputStream is = new FileInputStream(file);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
		IOUtils.copy(is, response.getOutputStream());
	}
    
    @RequestMapping(value = "/proof/view", method = RequestMethod.GET)
	public ModelAndView viewProof(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) {
    	ProofForm form = proofService.getProofById(id);
    	return new ModelAndView("proof_view", "proof", form);
    }
}
