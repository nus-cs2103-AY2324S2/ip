package task;
enum Command{
    TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, DELETE, INVALID;

    public static Command getCommand(String command) {
        try {
            return valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}