package io;

/**
 * Class that takes in messages and outputs them in the proper format.
 */
public class Outputter {
    public static void outputMessage(Message msg) {
        System.out.println(msg.getMessage());
    }
}
