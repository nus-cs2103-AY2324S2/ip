package objects;

import java.io.Serializable;

public class ToDos extends Task implements Serializable {
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
