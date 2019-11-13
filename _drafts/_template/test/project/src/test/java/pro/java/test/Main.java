package pro.java.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void main() {
        String s = "Hello";
        assertEquals(s, "Hello");
    }

    @Test
    public void testTree() {
        TreeMap map = new TreeMap();
        map.put(new TestObject("1"), "Second");
        map.put(new TestObject("3"), "Third");
        map.put(new TestObject("2"), "First");

        String s = Arrays.toString(map.keySet().toArray());
        System.out.println(s);
    }
}

class TestObject{
    private String name;

    public TestObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
