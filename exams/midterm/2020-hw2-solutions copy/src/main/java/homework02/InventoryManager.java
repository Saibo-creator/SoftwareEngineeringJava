// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

package homework02;

import homework02.util.InventoryObserver;
import homework02.util.Manager;
import homework02.util.Movement;
import homework02.util.MovementType;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link Manager} interface. See the interface documentation.
 */
public class InventoryManager implements Manager {

    private InventoryDatabase db;
    private List<InventoryObserver> observers = new ArrayList<>();
    /**
     * Creates a new manager for the given database.
     *
     * @param database The database, which cannot be null
     */
    public InventoryManager(InventoryDatabase database) {
        if(database==null){
            throw new IllegalArgumentException("The database cannot be null");
        }
        this.db=database;
    }

    @Override
    public void add(ItemKind itemKind) {
        if(itemKind==null){
            throw new IllegalArgumentException("The item kind cannot be null");
        }
        int curNum=this.db.get(itemKind);
        this.db.update(itemKind, Math.addExact(curNum,1));

        Movement movement=new Movement(itemKind, MovementType.ADD);
        notifyObservers(movement);
    }

    @Override
    public boolean take(ItemKind itemKind) {
        if(itemKind==null){
            throw new IllegalArgumentException("The item kind cannot be null");
        }
        int curNum=db.get(itemKind);
        if (curNum==0){
            return false;
        }else{
            db.update(itemKind, Math.subtractExact(curNum,1));
            Movement movement=new Movement(itemKind, MovementType.REMOVE);
            notifyObservers(movement);
            return true;
        }

    }

    @Override
    public int getQuantity(ItemKind itemKind) {
        if(itemKind==null){
            throw new IllegalArgumentException("The item kind cannot be null");
        }else {
            return db.get(itemKind);
        }
    }

    @Override
    public void addObserver(InventoryObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("The observer cannot be null");
        }
        if (observers.contains(observer)) return ;
        observers.add(observer);
    }

    public void notifyObservers(Movement movement){
        for (final InventoryObserver o : observers) { // added
            o.update(movement);                  // added
        }
    }
}