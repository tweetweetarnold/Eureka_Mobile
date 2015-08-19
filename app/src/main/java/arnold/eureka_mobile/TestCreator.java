package arnold.eureka_mobile;

import arnold.eureka_mobile.Model.User;

/**
 * Created by Arnold on 8/18/2015.
 */
public class TestCreator {

    public static User getTestUser(){
        return new User("abc", "123", "Arnold");
    }
}
