package ch.epfl.sweng.tests;

// !!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN edit this file.
// You CAN delete this file.
// !!!!!!!!!!!!!!!!!!!!!!!!!

import ch.epfl.sweng.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public final class StandardUserTests {
    private Forum forum;

    @Before
    public void setup() {
        forum = new Forum();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateStandardUserWithNullName() {
        new StandardUser(null);
    }

    @Test
    public void standardUserCanAskLongQuestion() {
        User standard = new StandardUser("Standard");

        assertThat(standard.canAsk("0123456789"), is(true));
    }

    @Test
    public void standardUserCanAnswerQuestionFromOthers() {
        User alice = new StandardUser("Alice");
        User bob = new StandardUser("Bob");

        forum.postQuestion(alice, "Question text");
        Question question = forum.getQuestions().get(0);

        assertThat(bob.canAnswer(question, "Answer text"), is(true));
    }

    @Test
    public void standardUserCanEditTheirPosts() {
        User standard = new StandardUser("Standard");

        forum.postQuestion(standard, "Question text");
        Question question = forum.getQuestions().get(0);

        assertThat(standard.canEdit(question, "New question text"), is(true));
    }

    @Test
    public void standardUserCanNotEditOthersPosts() {
        User standard = new StandardUser("Standard");
        User standard1 = new StandardUser("Standard1");

        forum.postQuestion(standard, "Question text");
        Question question = forum.getQuestions().get(0);

        assertThat(standard1.canEdit(question, "New question text"), is(false));
    }

    @Test
    public void standardUserCanNotEditTheirPostsWithAnswer() {
        User standard = new StandardUser("Standard");
        User standard1 = new StandardUser("Standard1");

        forum.postQuestion(standard, "Question text");
        Question question = forum.getQuestions().get(0);
        forum.postAnswer(standard1,question,"answer");

        assertThat(standard.canEdit(question, "New question text"), is(false));
    }

    @Test
    public void standardUserCanNotEditTheirAnswer() {
        User standard = new StandardUser("Standard");
        User standard1 = new StandardUser("Standard1");

        forum.postQuestion(standard, "Question text");
        Question question = forum.getQuestions().get(0);
        forum.postAnswer(standard1,question,"answer");
        Answer answer = question.getAnswers().get(0);
        standard1.canEdit(answer, "New answer text");

//        assertThat(standard.canEdit(question, "New question text"), is(false));
    }
}