package ch.epfl.sweng;

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CANNOT edit or remove the signatures of the existing methods/constructors.
// You CANNOT add new constructors.
// You CAN change the implementation of the methods/constructors.
// You CAN add new methods.
// You CAN add interface implementations.
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

/**
 * A limited users, which is an user that is banned from using a specific word:
 * - The word cannot appear in a question text.
 * - The word cannot appear in an answer text.
 * - The word cannot be edited in.
 * - The word must be detected in a case-insensitive manner.
 * Aside from these limitations, limited users inherit their wrapped user's permissions.
 * The banned word is "Travis".
 */
public class LimitedUser extends User {
    /**
     * Constructs a new limited user wrapping the specified user.
     *
     * @throws IllegalArgumentException if the user is null.
     */

    private final User wrappedUser;
    private final static String BAN_WORD =  "travis";

    public LimitedUser(User user) {
        if (user==null) throw new IllegalArgumentException();
        wrappedUser=user;
    }

    // See the base class' documentation for the other methods

    @Override
    public String getName() {
        return wrappedUser.getName();
    }

    @Override
    public boolean canAsk(String text) {
        if (text.toLowerCase().contains(BAN_WORD)) return false;
        return wrappedUser.canAsk(text);
    }

    @Override
    public boolean canAnswer(Question question, String text) {
        if (text.toLowerCase().contains(BAN_WORD)) return false;
        return wrappedUser.canAnswer(question,text);
    }

    @Override
    public boolean canEdit(Post post, String text) {
        if (text.toLowerCase().contains(BAN_WORD)) return false;
        return wrappedUser.canEdit(post,text);
    }
}
