package messages;

enum Commands {  // default access modifier
    EXIT("bye"),
    LIST("list"),
    ADD("add");

    private String command;

    Commands(String command) {  // default access modifier
        this.command = command;
    }

    static Commands getCommand(String input) {  // default access modifier
        for (Commands command : Commands.values()) {
            if (command.command.equals(input)) {
                return command;
            }
        }
        return ADD;
    }
}
