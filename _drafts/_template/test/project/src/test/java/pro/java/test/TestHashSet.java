package pro.java.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class TestHashSet {

    @Test
    public void main() throws NoSuchFieldException, IllegalAccessException {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Test");

        //LinkedHashSet --> HashSet -> HashMap with only keys -> array "table"
        // ArrayList result = new ArrayList();
        findField(set, "map");


    }

    private void printFields(Object object) {
        for (Class<?> c = object.getClass(); c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field classField : fields) {
                System.out.println(classField);
            }
        }
    }

    public static void findField(Object object, String name) throws IllegalAccessException {
        for (Class<?> c = object.getClass(); c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field classField : fields) {
                if (classField.getName().equals(name)) {
                    classField.setAccessible(true);
                    Object value = classField.get(object);
                    if (classField.getName().equals("table")) {
                        System.out.println(Arrays.deepToString((Object[]) value));
                    }
                    findField(value, "table");
                }
            }
        }
    }


}
