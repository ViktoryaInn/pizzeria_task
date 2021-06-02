package pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzeria.dbService.dataSets.Usr;
import pizzeria.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("login") String login,
                                     @ModelAttribute("password") String password) {
        System.out.println(login + " " + password);
        ModelAndView modelAndView = new ModelAndView();
        if(userService.findByLogin(login) == null){
            userService.register(new Usr(login, password, "USER"));
            modelAndView.setViewName("login");
        }else {
            modelAndView.setViewName("registration");
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }
}
