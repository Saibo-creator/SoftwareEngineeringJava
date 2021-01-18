public class SteamedMilk extends Beverage{
    private  Beverage beverage;

    public SteamedMilk(Beverage beverage) {
        this.beverage = beverage;
        description = beverage.description+", SteamedMilk";

    }

    @Override
    public double cost() {
        return this.beverage.cost()+0.1;
    }
}