package Dude;
enum Actions {
    DELETE,
    ADD,
    DONE,
    UNDONE,
    EVENT,
    DEADLINE,
    TODO,
    LIST,
    BYE,
    FIND
}

/**
 * Represents a command issued by the user, encapsulating an action and optional
 * additional information.
 */
class Command {
    public Actions action;

    public String info;

    public Command(Actions action, String info) {
        this.action = action;
        this.info = info;
    }

    public Command(Actions action) {
        this.action = action;
    }

}