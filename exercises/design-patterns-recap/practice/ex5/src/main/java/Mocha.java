public class Mocha extends Beverage{
    private  Beverage beverage;


    public Mocha(Beverage beverage) {
        this.beverage = beverage;
        description = beverage.description+", Mocha";

    }

    @Override
    public double cost() {
        return this.beverage.cost()+0.2;
    }
}
