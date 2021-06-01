package pizzeria.dbService;

import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.dataSets.Order;
import pizzeria.dbService.dataSets.Usr;

public class DBAccess {
    private final DBService dbService = new DBService();

    public Ingredient[] getIngredientsList(){
        return dbService.getListIngredient();
    }

    public Ingredient getIngredient(String id){
        return dbService.getIngredient(id);
    }

    public void addIngredient(Ingredient ingredient){
        dbService.addIngredient(ingredient);
    }

    public void updateIngredient(Ingredient ingredient){
        dbService.updateIngredient(ingredient);
    }

    public void deleteIngredient(String id){
        dbService.deleteIngredient(id);
    }

    public Order[] getOrderList(){
        return dbService.getListOrder();
    }

    public Ingredient[] getIngredientsByOrder(String id){
        return dbService.getIngredientsByOrder(id);
    }

    public void addOrder(Order order){
        dbService.addOrder(order);
    }

    public void addIngredientToOrder(String orderId, String ingredientId){
        dbService.addIngredientToOrder(orderId, ingredientId);
    }

    public void addUser(Usr user){
        dbService.addUser(user);
    }

    public Usr getUser(String login){
        return dbService.getUser(login);
    }
}
