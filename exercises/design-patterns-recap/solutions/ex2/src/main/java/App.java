public class App {
    public static void main(String[] args) {
        Director director = new Director();
        ComputerBuilder builder = new ComputerBuilder();
        Computer pc1 = director.buildMyAwesomePC(builder);
        System.out.println(pc1);
    }
}
