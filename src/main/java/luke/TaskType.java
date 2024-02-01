package luke;

public enum TaskType {
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event");

    private final String nameString;
    TaskType(String nameString) {
        this.nameString = nameString;
    }

    @Override
    public String toString() {
        return nameString;
    }
}
