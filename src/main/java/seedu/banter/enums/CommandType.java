package seedu.banter.enums;

public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
