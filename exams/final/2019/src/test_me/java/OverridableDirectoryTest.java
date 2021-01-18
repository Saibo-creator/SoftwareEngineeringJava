import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OverridableDirectoryTest {
    private DummyDirectory wrapped;
    private Map<String, Person> overrides;
    private OverridableDirectory overridableDirectory;

    @BeforeEach
    public void setup() {
        wrapped = new DummyDirectory();
        overrides = new HashMap<>();
        overridableDirectory = new OverridableDirectory(wrapped,overrides);


        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");
        Person thomas=new Person("Thomas","student",24,"Ecublens");
        wrapped.addRecord("jack", Arrays.asList(jack,joe));
        wrapped.addRecord("thomas", Arrays.asList(thomas));


        overrides.put("me",jack);
        overrides.put("TA",thomas);

    }

    @Test
    public void IllegalArgumentException1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new OverridableDirectory(null,null);;
        });

        String expectedMessage = "wrapped directory is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void IllegalArgumentException2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new OverridableDirectory(wrapped,null);;
        });

        String expectedMessage = "overrides map is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void IllegalArgumentException3() {
        overrides.put("friend",null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new OverridableDirectory(wrapped,overrides);;
        });

        String expectedMessage = "overrides map contains null values";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void IllegalArgumentException4() {
        Person jack=new Person("Jack","student",22,"Lausanne");
        overrides.put("me ",jack);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new OverridableDirectory(wrapped,overrides);;
        });

        String expectedMessage = "overrides map contains multiple keys that are equal given the comparison criteria.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



    @Test
    public void no_overrides_search_test() {

        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");


        assertThat(overridableDirectory.search("jack"), is(Arrays.asList(jack,joe)));

    }

    @Test
    public void overrides_search_test() {

        Person jack=new Person("Jack","student",22,"Lausanne");


        assertThat(overridableDirectory.search("me"), is(Arrays.asList(jack)));

    }
//
//    @Test
//    public void search_test_normalize() {
//
//        Person jack=new Person("Jack","student",22,"Lausanne");
//        Person joe=new Person("joe","student",24,"Lausanne");
//
//
//        assertThat(cachingDirectory.search("  JACK  "), is(Arrays.asList(jack,joe)));
//
//    }
//
//    @Test
//    public void search_cache_test() {
//
//        Person jack=new Person("Jack","student",22,"Lausanne");
//        Person joe=new Person("joe","student",24,"Lausanne");
//
//        cachingDirectory.search("jack");
//        assertThat(cachingDirectory.search("jack"), is(Arrays.asList(jack,joe)));
//
//    }

//    @Test
//    public void normalize_name(){
//
//
//
//        assertThat( cachingDirectory.normlize("  JACK  "), is("jack"));
//    }

}
