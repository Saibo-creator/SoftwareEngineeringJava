import io.javalin.Javalin;

import java.util.Optional;

import models.ForumPost;
import models.ForumState;
import models.ForumThread;
import views.ForumView;
import views.ThreadView;
import views.View;

public class App {
    private static ForumState state = new ForumState();

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        
        app.get("/", ctx -> {
            final View view = new ForumView(state.getAllThreads());
            ctx.html(view.render());
        });

        app.get("/forum", ctx -> {
            final String threadId = ctx.queryParam("id");
            final Optional<ForumThread> data = state.getThread(threadId);
            if (data.isPresent()) {
                final View view = new ThreadView(data.get());
                ctx.html(view.render());
            } else {
                ctx.result("This forum does not exist");
            }
        });

        app.post("/new", ctx -> {
            String author = ctx.formParam("author");
            String title = ctx.formParam("title");
            ForumThread newThread = new ForumThread(author, title);
            state.addThread(newThread);
            ctx.redirect("/");
        });

        app.post("/reply", ctx -> {
            String author = ctx.formParam("author");
            String body = ctx.formParam("body");
            String threadId = ctx.formParam("threadId");

            ForumPost newPost = new ForumPost(author, body);
            final Optional<ForumThread> thread = state.getThread(threadId);
            if (thread.isPresent()) {
                thread.get().addPost(newPost);
            }

            ctx.redirect("/forum?id=" + threadId);
        });
    }
}