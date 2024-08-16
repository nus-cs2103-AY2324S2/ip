package remi.io;

/**
 * Basic class to carry
 */
public class Message {
    private final String data;

    /**
     * Basic constructor that takes in a String and constructs a Message object.
     * @param data string containing the message data
     */
    public Message(String data) {
        this.data = data;
    }

    /**
     * Simple getter for the message.
     * @return the message stored in the object
     */
    public String getMessage() {
        return this.data;
    }
}
