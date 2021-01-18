import static spark.Spark.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MultiThreadedServer {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(4);
    public static void main(String[] args) {
        int maxThreads = 8;
        threadPool(maxThreads);
        get("/hello", (req, res) -> "Hello World hahaha");
        get("/loop", (req, res) -> {
            threadPool.execute(()->{
                int i=0;
                while(i<Integer.MAX_VALUE){
                    i++;
                }
            });

            System.out.println("done");
            return "from Multithread";
        });
    }
}
