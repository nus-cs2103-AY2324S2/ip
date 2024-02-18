package luke;

public enum Command {
    BYE("bye"),
    LIST("list"),
    DELETE("delete"),
    MARK("mark"),
    FIND("find"),
    REMIND("remind");
    private final String nameString;
    Command(String nameString) {
        this.nameString = nameString;
    }

    @Override
    public String toString() {
        return nameString;
    }
}
