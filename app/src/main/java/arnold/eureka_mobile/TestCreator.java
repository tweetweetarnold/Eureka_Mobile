package arnold.eureka_mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arnold.eureka_mobile.Entity.Canteen;
import arnold.eureka_mobile.Entity.Employee;
import arnold.eureka_mobile.Entity.Hawker;

/**
 * Created by Arnold on 8/18/2015.
 */
public class TestCreator {

    public static Employee getTestUser(){
        return new Employee("abc", "123", "Arnold", null, 45678945, null, null, new Date());
    }

    public static ArrayList<Canteen> getTestCanteenList(){
        ArrayList<Canteen> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(new Canteen("Canteen " + i, i + " Street", null, new Date(), getTestHawkerList()));
        }
        return list;
    }

    public static ArrayList<Hawker> getTestHawkerList(){
        ArrayList<Hawker> list = new ArrayList<>();
        list.add(new Hawker("hawker1", "123", "Hawker 1", 123, null, new Date(), null, null));
        list.add(new Hawker("hawker2", "123", "Hawker 2", 123, null, new Date(), null, null));
        list.add(new Hawker("hawker3", "123", "Hawker 3", 123, null, new Date(), null, null));
        return list;
    }

}
