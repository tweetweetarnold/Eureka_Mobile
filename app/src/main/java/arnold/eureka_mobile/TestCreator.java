package arnold.eureka_mobile;

import java.util.ArrayList;
import java.util.Date;

import arnold.eureka_mobile.Entity.Canteen;
import arnold.eureka_mobile.Entity.Employee;
import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.Entity.Stall;

/**
 * Created by Arnold on 8/18/2015.
 */
public class TestCreator {

    public static Employee getTestUser(){
        return new Employee("abc", "123", "Arnold", null, 45678945, null, null, new Date());
    }

    public static ArrayList<Canteen> getTestCanteenList(){
        ArrayList<Canteen> list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            list.add(new Canteen("Canteen " + i, i + " Street", null, new Date(), getTestHawkerList()));
        }
        return list;
    }

    public static ArrayList<Stall> getTestHawkerList(){
        ArrayList<Stall> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(new Stall("hawker" + i, "123", "Stall " + i, 123, null, new Date(), getTestFoodList(), null));
        }
        return list;
    }

    public static ArrayList<Food> getTestFoodList(){
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Chicken Rice", "Rice", 3.00, null, new Date()));
        list.add(new Food("Wanton Mee", "Noodle", 4.50, null, new Date()));
        list.add(new Food("Abalone", "Seafood", 6.50, null, new Date()));
        list.add(new Food("Crab", "Seafood", 6.50, null, new Date()));
        list.add(new Food("Lobster", "Seafood", 6.50, null, new Date()));
        list.add(new Food("Carrot Cake", "Cake", 6.50, null, new Date()));
        list.add(new Food("Ba Chor Mee", "Noodle", 6.50, null, new Date()));
        list.add(new Food("Laksa", "Noodle", 6.50, null, new Date()));
        list.add(new Food("Smelly Cat", "Exotic", 6.50, null, new Date()));
        return list;
    }

    public static ArrayList<Food> getTestFavouriteList(){
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("McSpicy", "Burger", 6.80, null, new Date()));
        list.add(new Food("McChicken", "Burger", 2.00, null, new Date()));
        list.add(new Food("Big Mac", "Burger", 7.80, null, new Date()));
        return list;
    }

}
