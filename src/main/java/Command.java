public enum Command {
    LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
