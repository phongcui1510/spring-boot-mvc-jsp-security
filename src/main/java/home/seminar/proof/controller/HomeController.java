package home.seminar.proof.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/", "/home"})
    public String getHomePage() {
        LOGGER.debug("Getting home page");
        return "home";
    }
    
    @RequestMapping("/tintuc")
    public String getTintucPage() {
        LOGGER.debug("Getting home page");
        return "tintuc";
    }
    
    @RequestMapping("/thongbao")
    public String getThongbaoPage() {
        LOGGER.debug("Getting home page");
        return "thongbao";
    }
    
    @RequestMapping("/nhapchucvu")
    public String getNhapchucvuPage() {
        LOGGER.debug("Getting home page");
        return "nhapchucvu";
    }
    
    @RequestMapping("/gioithieu")
    public String getGioithieuPage() {
        LOGGER.debug("Getting home page");
        return "gioithieu";
    }

}
