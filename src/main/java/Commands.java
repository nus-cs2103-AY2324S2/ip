public enum Commands {
    BYE("bye");

    private final String cmd;

    Commands (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return this.cmd;
    }
}
