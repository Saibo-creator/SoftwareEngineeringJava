
import java.util.ArrayList;
import java.util.List;

public class ShapeComposite extends Shape {
    private final List<Shape> shapeList = new ArrayList<>();

    public void add(Shape shape){
        shapeList.add(shape);
    }
    public void color(String color){
        if (!color.equals("black")) throw new IllegalArgumentException();
        for (Shape shape:shapeList) {
            shape.color(color);
        }
    }
}
