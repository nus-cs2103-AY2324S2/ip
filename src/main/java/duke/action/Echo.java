package duke.action;

public class Echo implements Action {
    private final String stuff;
    public Echo(String stuff) {
        this.stuff = stuff;
    }

    @Override
    public String response() {
        return "  " + "added: " + stuff;
    }
}
