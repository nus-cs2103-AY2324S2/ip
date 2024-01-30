package objects;

import java.io.Serializable;

public class Events extends Task implements Serializable {
    private final String from;
    private final String to;

    public Events(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
