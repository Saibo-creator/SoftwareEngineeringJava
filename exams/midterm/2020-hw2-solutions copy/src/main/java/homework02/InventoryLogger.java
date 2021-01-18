// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

package homework02;

import homework02.util.Logger;
import homework02.util.Manager;
import homework02.util.Movement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link Logger} interface. See the interface documentation.
 */
public class InventoryLogger implements Logger {

    private List<Movement> movements=new ArrayList<Movement>();
    private Manager manager;

    /**
     * Creates a new logger for the given manager.
     *
     * @param manager The manager, which cannot be null
     */
    public InventoryLogger(Manager manager) {
        if(manager==null){
            throw new IllegalArgumentException("observable manager must not be null");
        }
        this.manager = manager;
        this.manager.addObserver(this);
    }

    @Override
    public List<String> getMovements() {
        List<String> movements_descrip= new ArrayList<>();
        for(Movement movement:this.movements){
            movements_descrip.add(movement.toString());
        }
        return movements_descrip;
    }

    @Override
    public void empty() {
        this.movements.clear();
    }

    @Override
    public void update(Movement movement) {
        this.movements.add(movement);
    }
}
