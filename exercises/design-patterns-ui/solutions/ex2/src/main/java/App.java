public class App {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.print();

        Logger2 logger2 = Logger2.INSTANCE;
        logger2.print();
    }
}
