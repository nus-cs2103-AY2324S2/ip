public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}