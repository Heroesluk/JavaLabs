package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreeterTest {
    Greeter greet = new Greeter(new MessageFormatter());


    @Test
    void greet() {
        assertEquals("", greet.greet(""));
        assertNull(greet.greet(null));
        assertEquals("Student", greet.greet("Student"));

    }
}