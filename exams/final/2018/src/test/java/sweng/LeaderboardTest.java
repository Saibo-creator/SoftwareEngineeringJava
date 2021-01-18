package sweng; 

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
    Leaderboard leaderboard = new Leaderboard(new Forum());
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
//TODO: Test goes here... 
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
