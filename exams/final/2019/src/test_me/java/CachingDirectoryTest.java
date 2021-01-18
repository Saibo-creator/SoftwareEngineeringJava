import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CachingDirectoryTest {
    private DummyDirectory wrapped;
    private CachingDirectory cachingDirectory;

    @BeforeEach
    public void setup() {
        wrapped = new DummyDirectory();
        cachingDirectory = new CachingDirectory(wrapped);


        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");
        Person thomas=new Person("Thomas","student",24,"Ecublens");
        wrapped.addRecord("jack", Arrays.asList(jack,joe));
        wrapped.addRecord("thomas", Arrays.asList(thomas));
    }



    @Test
    public void search_test() {

        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");


        assertThat(cachingDirectory.search("jack"), is(Arrays.asList(jack,joe)));

    }

    @Test
    public void IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cachingDirectory = new CachingDirectory(null);;
        });

        String expectedMessage = "wrapped directory is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void search_test_normalize() {

        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");


        assertThat(cachingDirectory.search("  JACK  "), is(Arrays.asList(jack,joe)));

    }

    @Test
    public void search_cache_test() {

        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");

        cachingDirectory.search("jack");
        assertThat(cachingDirectory.search("jack"), is(Arrays.asList(jack,joe)));

    }

//    @Test
//    public void normalize_name(){
//
//
//
//        assertThat( cachingDirectory.normlize("  JACK  "), is("jack"));
//    }

}
