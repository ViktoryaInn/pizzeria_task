package pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.service.IngredientService;

@Controller
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/ingredients/add")
    public ModelAndView getAddViewIngredient(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addIngredient");
        return modelAndView;
    }

    @PostMapping("/ingredients/add")
    public String addIngredient(@ModelAttribute("name") String name,
                                     @ModelAttribute("price") String price){
        Ingredient ingredient = new Ingredient(name, Integer.parseInt(price));
        ingredientService.addIngredient(ingredient);
        return "redirect:/";
    }

    @GetMapping("/ingredients/change/{id}")
    public ModelAndView getChangeViewIngredient(@PathVariable("id") String id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ingredient", ingredient);
        modelAndView.setViewName("changeIngredient");
        return modelAndView;
    }

    @PostMapping("/ingredients/change/{id}")
    public String changeIngredient(@PathVariable("id") String pathId,
                                 @ModelAttribute("name") String name,
                                 @ModelAttribute("price") String price){

        Ingredient ingredient = new Ingredient(pathId, name, Integer.parseInt(price));
        ingredientService.updateIngredient(ingredient);
        return "redirect:/";
    }

    @GetMapping( "/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") String id){
        ingredientService.deleteIngredient(id);
        return "redirect:/";
    }
}
