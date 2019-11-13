package pro.java.libraries;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import pro.java.libraries.domain.Box;
import pro.java.libraries.domain.Box2;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class BeanUtilsTest {

    @Test
    public void copyProperties() throws InvocationTargetException, IllegalAccessException {
        Box orig = new Box();
        orig.setName("Box");

        Box dest = new Box();
        BeanUtils.copyProperties(dest, orig);

        assertEquals("Box", dest.getName());
    }

    @Test(expected = AssertionError.class)
    public void copyProperties2() throws InvocationTargetException, IllegalAccessException {
        Box2 orig = new Box2();
        orig.setName("Box");

        Box2 dest = new Box2();
        BeanUtils.copyProperties(dest, orig);

        assertEquals("Box", dest.getName()); // throws AssertionError
    }
}