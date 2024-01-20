package messages;

class Requests {

    // String constants
    private static final String EXIT = "bye";

    // Methods
    boolean isExit(String input) {  // default access modifier
        return input.equals(EXIT);
    }
}
