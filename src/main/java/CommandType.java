public enum CommandType {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    BYE("bye");

    private final String stringValue;

    CommandType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
