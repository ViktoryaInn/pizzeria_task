package pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
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

//    @RequestMapping(path = "/ingredients/change/{id}", method = RequestMethod.POST)
//    public View changeIngredient(@PathVariable("id") String id,
//                                 @ModelAttribute("id") String ingredientId,
//                                 @ModelAttribute("name") String name,
//                                 @ModelAttribute("price") String price){
//        System.out.println(ingredientId + " " + name + " " + price);
//        Ingredient ingredient = new Ingredient(ingredientId, name, Integer.parseInt(price));
//        ingredientService.updateIngredient(ingredient);
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName("index");
//        RedirectView redirect = new RedirectView("/");
//        redirect.setExposeModelAttributes(false);
//        return redirect;
//    }

    @GetMapping( "/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") String id){
        ingredientService.deleteIngredient(id);
        return "redirect:/";
    }

//    @RequestMapping(path = "/ingredients/delete/{id}", method = RequestMethod.POST)
//    public void deleteIngredient(@PathVariable("id") String id){
//        ingredientService.deleteIngredient(id);
//    }

//    @PostMapping("/")
//    public ModelAndView getIngredientsList(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        Ingredient[] ingredients = ingredientService.getIngredientsList();
//        ModelAndView modelAndView = new ModelAndView();
//        if(!currentUserName.equals("anonymousUser")){
//            modelAndView.addObject("ingredients", ingredients);
//        }
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
}
