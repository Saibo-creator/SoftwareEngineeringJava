// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

package homework02;

import homework02.util.Manager;
import homework02.util.Movement;
import homework02.util.Supplier;

/**
 * Implementation of the {@link Supplier} interface. See the interface documentation.
 */
public class InventorySupplier implements Supplier {
    private final Manager manager;
    private int threshold;
    private int supplyQuantity;
    /**
     * Creates a new supplier for the given manager.
     *
     * @param manager The manager, which cannot be null
     */
    public InventorySupplier(Manager manager) {
        if(manager==null){
            throw new IllegalArgumentException("observable manager must not be null");
        }
        this.manager = manager;
        this.manager.addObserver(this);
    }

    @Override
    public void setThreshold(int threshold) {
        if (threshold<0){
            throw new IllegalArgumentException("threshold must be larger than 0");
        }
        this.threshold=threshold;
    }

    @Override
    public void setSupplyQuantity(int supplyQuantity) {
        if (supplyQuantity<0){
            throw new IllegalArgumentException("supply quantity must be larger than 0");
        }
        if (supplyQuantity==0){
            setThreshold(0);
        }
        this.supplyQuantity=supplyQuantity;
    }

    @Override
    public void update(Movement movement) {
        ItemKind itemKind=movement.itemKind;
        while (manager.getQuantity(itemKind)<threshold){
            int count=0;
            while(count<supplyQuantity){
                manager.add(itemKind);
                count++;
            }
        }
    }
}
