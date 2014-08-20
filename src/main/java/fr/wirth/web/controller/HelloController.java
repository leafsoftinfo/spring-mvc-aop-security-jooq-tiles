package fr.wirth.web.controller;

import fr.wirth.web.dao.UtilisateurDao;
import fr.wirth.web.dao.UtilisateurDao.Utilisateur;
import fr.wirth.web.service.ISecurityService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    
    @Autowired
    private ISecurityService securityService;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
        model.put("title", "Spring Security Hello World");
        model.put("message", "This is welcome page!");
        return "hello";
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String adminPage(Principal principal, ModelMap model) {
        model.put("title", "Spring Security Hello World");
        model.put("message", "This is protected page!");
        model.put("username", principal.getName());
        return "admin";
    }
    
    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String register(ModelMap map){
        map.put("formObject", new UtilisateurDao.Utilisateur());
        return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register2(@ModelAttribute(value = "formObject") Utilisateur user){
        securityService.register(user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {

        if (error != null && !"".equals(error.trim())) {
            model.put("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.put("msg", "You've been logged out successfully.");
        }
        return "login";
    }
    
    @Secured({"ROLE_USER"})
    @RequestMapping(value="/admin/ok", method = RequestMethod.GET)
    public String ok(){
        return "ok";
    }
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value="/admin/ko", method = RequestMethod.GET)
    public String ko(){
        return "ko";
    }

}
