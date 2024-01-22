public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}