package pro.java.test;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Serzh Nosov created on 12.01.2020.
 */
public class GenericRunTime {

    List<String> stringList = new ArrayList<String>();
    List<Integer> integerList = new ArrayList<Integer>();

    public List<String> test(List<String> se) {
        return null;
    }

    @Test
    public void main() throws NoSuchFieldException {

        TreeMap<Object, Object> map = new TreeMap<>();
        map.put(new Object(), new Object());
        map.put(new Object(), new Object());
        Object o = map.get(1);


        /*for (Method method : GenericRunTime.class.getMethods()) {
            Class returnClass = method.getReturnType();
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (Collection.class.isAssignableFrom(returnClass)) {
                Type returnType = method.getGenericReturnType();
                if (returnType instanceof ParameterizedType) {
                    ParameterizedType paramType = (ParameterizedType) returnType;
                    Type[] argTypes = paramType.getActualTypeArguments();
                    if (argTypes.length > 0) {
                        System.out.println("Generic type is " + argTypes[0]);
                    }
                }
            }
        }*/

        /*List<String> stringList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();

        Field stringListField = GenericRunTime.class.getDeclaredField("stringList");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = GenericRunTime.class.getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.*/
    }

}
