public class Logger {

    private Logger logger
    private Logger();

    public static Logger getLogger(){
        if (this.logger==null){
            return new Logger();
        }else{
            return this.logger;
        }

    }

    public void print() {
        System.out.println("Logged");
    }
}
