public class Whip extends Beverage{
    private  Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
        description = beverage.description+", WhippedMilk";

    }

    @Override
    public double cost() {
        return this.beverage.cost()+0.2;
    }
}