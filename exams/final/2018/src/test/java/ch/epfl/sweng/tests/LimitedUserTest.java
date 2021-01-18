package ch.epfl.sweng.tests;

import ch.epfl.sweng.*;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/** 
* LimitedUser Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 14, 2021</pre> 
* @version 1.0 
*/ 
public class LimitedUserTest {
    Forum forum = new Forum();
    StandardUser standardUser = new StandardUser("saibo");
    StandardUser standardUser2 = new StandardUser("jack");

    LimitedUser limitedUser = new LimitedUser(standardUser);

@Before
public void before() throws Exception {
    forum.postQuestion(standardUser2, "1+1=2?             ");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    assertThat(limitedUser.getName(), is("saibo"));
}

/** 
* 
* Method: canAsk(String text) 
* 
*/ 
@Test
public void testCanAsk() throws Exception {
    assertThat(limitedUser.canAsk("1+1=2                    ?"), is(true));
    assertThat(limitedUser.canAsk("1+1=2              travis      ?"), is(false));
    assertThat(limitedUser.canAsk("1+1=2              travIs      ?"), is(false));
} 

/** 
* 
* Method: canAnswer(Question question, String text) 
* 
*/ 
@Test
public void testCanAnswer() throws Exception {
    assertThat(limitedUser.canAnswer(forum.getQuestions().get(0),"yepppppp"), is(true));
    assertThat(limitedUser.canAnswer(forum.getQuestions().get(0),"yepppppp Travis"), is(false));
    assertThat(limitedUser.canAnswer(forum.getQuestions().get(0),"yepppppp tRAvis"), is(false));
} 

/** 
* 
* Method: canEdit(Post post, String text) 
* 
*/ 
@Test
public void testCanEdit() throws Exception {
    boolean ground_true = standardUser.canEdit(forum.getQuestions().get(0),"no");
    assertThat(limitedUser.canEdit(forum.getQuestions().get(0),"no"), is(ground_true));
} 


} 
