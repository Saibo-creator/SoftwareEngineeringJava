import java.util.ArrayList;
import java.util.List;

public interface TextConverter {

    List<String> getOutput();
    


    void turn(HyperLink hyperLink);

    void turn(PlainText plainText);

    void turn(BoldText boldText);
}
