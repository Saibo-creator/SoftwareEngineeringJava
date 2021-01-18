package ch.epfl.sweng.tests;

import ch.epfl.sweng.Forum;
import ch.epfl.sweng.LimitedUser;
import ch.epfl.sweng.Moderator;
import ch.epfl.sweng.StandardUser;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/** 
* Moderator Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 14, 2021</pre> 
* @version 1.0 
*/ 
public class ModeratorTest {
    Forum forum = new Forum();
    StandardUser standardUser = new StandardUser("saibo");
    StandardUser standardUser2 = new StandardUser("jack");

    Moderator moderator = new Moderator(standardUser);

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
    assertThat(moderator.getName(), is("saibo"));
} 

/** 
* 
* Method: canAsk(String text) 
* 
*/ 
@Test
public void testCanAsk() throws Exception {
    assertThat(moderator.canAsk("1+1=2                    ?"), is(true));
    assertThat(moderator.canAsk("1+1=2?"), is(false));
//    assertThat(moderator.canAsk("1+1=2              travIs      ?"), is(false));
} 

/** 
* 
* Method: canAnswer(Question question, String text) 
* 
*/ 
@Test
public void testCanAnswer() throws Exception {
    assertThat(moderator.canAnswer(forum.getQuestions().get(0),"yepppppp"), is(true));
    assertThat(moderator.canAnswer(forum.getQuestions().get(0),"yepppppp Travis"), is(true));
    assertThat(moderator.canAnswer(forum.getQuestions().get(0),"yepppppp tRAvis"), is(true));
} 

/** 
* 
* Method: canEdit(Post post, String text) 
* 
*/ 
@Test
public void testCanEdit() throws Exception {
    assertThat(moderator.canEdit(forum.getQuestions().get(0),"no"), is(true));
} 


} 
