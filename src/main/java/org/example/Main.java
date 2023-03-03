package org.example;

import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Greeter greet = new Greeter(new MessageFormatter());
        System.out.println(greet.greet("a"));

    }
}

class Greeter {
    MessageFormatter formatter;
    Greeter(MessageFormatter formatter) {
        this.formatter = formatter;

    }

    String greet(String who) {
        return formatter.format(who);
    }


}

class MessageFormatter{
    String format(String message){
        return message;
    }
}

