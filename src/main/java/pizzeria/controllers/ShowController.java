package pizzeria.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.service.IngredientService;
import pizzeria.service.UserService;

import java.util.List;

@Controller
public class ShowController {

    static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        ModelAndView modelAndView = new ModelAndView();
        logger.info("Главная страница");
        if(!currentUserName.equals("anonymousUser")){
            modelAndView.addObject("user", currentUserName);
            Ingredient[] ingredients = ingredientService.getIngredientsList();
            modelAndView.addObject("ingredients", ingredients);
            String currentUserRole = userService.findByLogin(currentUserName).getRole();
            modelAndView.addObject("userRole", currentUserRole);
            logger.info("Выведены все ингредиенты");
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
