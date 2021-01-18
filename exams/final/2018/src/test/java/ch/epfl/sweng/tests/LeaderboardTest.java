package ch.epfl.sweng.tests;


import ch.epfl.sweng.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leaderboard Tester.
 *
 * @author <Authors name>
 * @since <pre>Jan 14, 2021</pre>
 * @version 1.0
 */
public class LeaderboardTest {
    Map<User,Integer> userPointMap = new HashMap<>();
    Forum forum = new Forum();
    Leaderboard leaderboard = new Leaderboard(forum);
    User user1 = new StandardUser("user1");
    User user2 = new StandardUser("user-a");
    User user3 = new StandardUser("user-b");


    @Before
    public void before() throws Exception {
        userPointMap.put(user1,10);
        userPointMap.put(user2,20);
        userPointMap.put(user3,20);
        leaderboard.setUserPointMap(userPointMap);
        

    }

    @After
    public void after() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotInitiateNullForum() {
        Leaderboard leaderboard = new Leaderboard(null);
    }

    /**
     *
     * Method: toString()
     *
     */
    @Test
    public void testToString() throws Exception {
        System.out.println(leaderboard.toString());

    }

    /**
     *
     * Method: update(Observable observable, Object arg)
     *
     */
    @Test
    public void testUpdate() throws Exception {
        forum.postQuestion(user1,"1+1=2        ?");
        forum.postQuestion(user2,"1+1=2        ?");
        forum.postAnswer(user1,forum.getQuestions().get(1),"1+1=2        ?");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CanNotUpdateWithNull() throws Exception {
        forum.postQuestion(user1,null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void CanNotUpdateWithNull2() throws Exception {
        forum.postQuestion(null,"yes");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CanNotUpdateWithNull3() throws Exception {
        forum.postQuestion(null,null);
    }

    /**
     *
     * Method: compare(Map.Entry<User, Integer> o1, Map.Entry<User, Integer> o2)
     *
     */
    @Test
    public void testCompare() throws Exception {
//TODO: Test goes here...
    }


    /**
     *
     * Method: sort()
     *
     */
    @Test
    public void testSort() throws Exception {


        List<Map.Entry<User, Integer>> list = new ArrayList<>(userPointMap.entrySet());
        list.sort(new UserComparator());
        userPointMap.put(user2,20);




//TODO: Test goes here...
/*
try {
   Method method = Leaderboard.getClass().getMethod("sort");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

}
