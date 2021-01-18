import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public final class AppViewTest {
    private  AppView appView;


    @Test
    public void AppViewtoString() {
        String description = "switch";
        List<String> commands = Arrays.asList("turn on","turn off");
        appView = new AppView(description,commands);

        appView.toString();
    }
    
    
    
}
