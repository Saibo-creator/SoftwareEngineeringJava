public class Soy extends Beverage{
    private  Beverage beverage;


    public Soy(Beverage beverage) {
        if (beverage==null) throw new IllegalArgumentException();
        this.beverage = beverage;
        description = beverage.description+", Soy";
    }

    @Override
    public double cost() {
        return this.beverage.cost()+0.15;
    }
}