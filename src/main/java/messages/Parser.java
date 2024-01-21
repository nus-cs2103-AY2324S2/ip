package messages;

class Parser {  // default access modifier
    static String getCommand(String input) {  // default access modifier
        return input.split(" ")[0];
    }

    static String getArgument(String input) {  // default access modifier
        return input.split(" ")[1];
    }
}
