package arnold.eureka_mobile;

import arnold.eureka_mobile.Entity.Employee;

/**
 * Created by Arnold on 8/18/2015.
 */
public class TestCreator {

    public static Employee getTestUser(){
        return new Employee("abc", "123", "Arnold");
    }
}
