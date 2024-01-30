package objects;

import java.io.Serializable;

public class Deadlines extends Task implements Serializable {
    private final String by;

    public Deadlines(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

}
