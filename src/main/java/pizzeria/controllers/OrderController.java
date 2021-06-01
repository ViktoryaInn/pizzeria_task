package pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.dataSets.Order;
import pizzeria.models.OrderDTO;
import pizzeria.service.IngredientService;
import pizzeria.service.OrderService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ModelAndView getOrders(){
        Order[] orders = orderService.getOrderList();
        if(orders.length == 0){
            // TODO: to do smth
        }
        LinkedList<OrderDTO> responseOrders = new LinkedList<>();
        for(Order order: orders){
            Ingredient[] ingredients = orderService.getIngredientsByOrder(order.getId());
            responseOrders.add(new OrderDTO(order.getClientName(), order.getClientPhone(), order.getCost(), order.getDate(), ingredients));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", responseOrders);
        modelAndView.setViewName("orderList");
        return modelAndView;
    }

    @PostMapping("/orders/add")
    public ModelAndView addOrder(@ModelAttribute("name") String name,
                                 @ModelAttribute("phone") String phone,
                                 @ModelAttribute("cost") String cost,
                                 @ModelAttribute("ingredients") String ingredientsString){
        Order order = new Order(name, phone, Integer.parseInt(cost), java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        orderService.addOrder(order);
        String[] ingredients = ingredientsString.split(" ");
        for(String ingredient: ingredients){
            orderService.addIngredientToOrder(order.getId(), ingredient);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("done"); // dont work
        return modelAndView;
    }
}
