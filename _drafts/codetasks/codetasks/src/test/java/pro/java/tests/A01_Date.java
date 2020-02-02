package pro.java.tests;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;
import static org.junit.Assert.assertEquals;

/**
 * Write a simple Java program which will print Fibonacci series, e.g. 1 1 2 3 5 8 13 ... up to a given number.
 * We prepare for cross questions like using iteration over recursion and how to optimize the solution using caching and memoization.
 */
public class A01_Date {

    private static final ThreadLocal<DateTimeFormatter> TABLE_TIME_STAMP_POSTFIX = withInitial(() ->
            DateTimeFormatter.ofPattern(("yyyyMMddHHmmss")));

    @Test
    public void date(){
        ZoneId zoneId = ZoneId.systemDefault();
        // 8/30/2018 13:29:19 -> session_16_cohort_20183008132919
        ZonedDateTime of = ZonedDateTime.of(2018, 8, 30, 13, 29, 19, 0, zoneId);
        String format = TABLE_TIME_STAMP_POSTFIX.get().format(of);
        System.out.println(format);

        //assertEquals("20183008132919", format);
        assertEquals("20180830132919", format);
    }
}
