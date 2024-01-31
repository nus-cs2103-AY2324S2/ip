package duke.utils;

public enum KeyEnum {
    EXITKEY("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"), EVENT("event"), DEADLINE("deadline"), TODO("todo"), DELETE("delete"),INVALID("invalid"), FIND("find");
    private String detail;

    KeyEnum(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return this.detail;
    }
}
