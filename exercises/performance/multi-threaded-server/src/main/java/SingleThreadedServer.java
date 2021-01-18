import static spark.Spark.*;

public class SingleThreadedServer {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World hahaha");
        get("/loop", (req, res) -> {
            int i=0;
            while(i<Integer.MAX_VALUE){
                i++;
            }
            System.out.println("done");
            return true;
        });
    }
}
