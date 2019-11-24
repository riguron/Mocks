package io.riguron.mocks.tests;

import io.riguron.mocks.Answer;
import io.riguron.mocks.classes.SomeInterface;
import io.riguron.mocks.invocation.Invocation;
import org.junit.jupiter.api.Test;

import static io.riguron.mocks.Mocks.*;
import static io.riguron.mocks.Mocks.doAnswer;
import static io.riguron.mocks.matcher.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockingOverridingTest {

    @Test
    void overrideWithSameArgsTwice() {

        SomeInterface someInterface = mock(SomeInterface.class);

        when(someInterface.getStringResult(eq(1), eq("1"))).thenReturn("A");

        assertEquals("A", someInterface.getStringResult(1, "1"));

        when(someInterface.getStringResult(eq(1), eq("1"))).thenReturn("B");

        assertEquals("B", someInterface.getStringResult(1, "1"));

        //

    }

    @Test
    void overridePrefixStyle() {
        SomeInterface someInterface = mock(SomeInterface.class);


        doAnswer(new Answer<String>() {
            @Override
            public String answer(Invocation mockInvocation) throws Throwable {
                return "X";
            }

            @Override
            public String toString() {
                return "1";
            }
        }).when(someInterface).getStringResult(eq(1), eq("2"));

        assertEquals("X", someInterface.getStringResult(1, "2"));

        doAnswer(new Answer<String>() {
            @Override
            public String answer(Invocation mockInvocation) throws Throwable {
                return "Y";
            }

            @Override
            public String toString() {
                return "2";
            }
        }).when(someInterface).getStringResult(eq(1), eq("2"));
        assertEquals("Y", someInterface.getStringResult(1, "2"));

    }
}
