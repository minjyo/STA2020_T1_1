package testpackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestClassTest {

    @Test
    public void setValue() {
        TestClass.setValue(2020);
        assertEquals(2020, TestClass.getValue());
    }
}