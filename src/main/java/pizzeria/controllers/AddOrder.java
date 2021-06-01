package pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pizzeria.dbService.DBService;
import pizzeria.dbService.dataSets.Ingredient;

import java.util.Map;

@Controller
public class AddOrder {
    DBService dbService = new DBService();

//    @GetMapping("/add")
//    public ModelAndView add(){
////        model.addAttribute("ingredients", dbService.getListIngredient());
//        ModelAndView modelAndView = new ModelAndView();
//        Ingredient[] ingredients = dbService.getListIngredient();
//        modelAndView.setViewName("addOrder");
//        modelAndView.addObject("ingredients", ingredients);
//        return modelAndView;
//    }

    @PostMapping("/add")
    public void add(@ModelAttribute("name") String name, @ModelAttribute("phone") String phone, @ModelAttribute("ingredients") String ingredients){
        System.out.println(name + " " + phone + " " + ingredients);
    }

//    @PostMapping("/add")
//    public void add(@RequestParam("name") String name,
//                     @RequestParam("phone") String phone){
//        System.out.println(name + " " + phone);
//    }

    @GetMapping("/add")
    public ModelAndView list(){
//        model.addAttribute("ingredients", dbService.getListIngredient());
        ModelAndView modelAndView = new ModelAndView();
        Ingredient[] ingredients = dbService.getListIngredient();
        modelAndView.setViewName("addOrder");
        modelAndView.addObject("ingredients", ingredients);
        return modelAndView;
    }

}
