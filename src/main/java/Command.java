public enum Command {

    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private String input;

    Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return this.input;
    }

}
