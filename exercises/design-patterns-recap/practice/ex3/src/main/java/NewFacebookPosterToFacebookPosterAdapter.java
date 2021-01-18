import java.util.Calendar;

public class NewFacebookPosterToFacebookPosterAdapter implements FacebookPoster{
    private  NewFacebookPoster newFacebookPoster;

    public NewFacebookPosterToFacebookPosterAdapter(NewFacebookPoster newFacebookPoster) {
        this.newFacebookPoster = newFacebookPoster;
    }

    @Override
    public long postMessage(String message) {
        return newFacebookPoster.post(new Post(message, Calendar.getInstance().getTime()));
    }

    @Override
    public String getMessage(long postId) {
        return newFacebookPoster.get(postId).getMessage();
    }
}
