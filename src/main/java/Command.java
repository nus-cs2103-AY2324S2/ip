enum Command{
    TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, INVALID;

    public static Command getCommand(String command) {
        try {
            return valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}