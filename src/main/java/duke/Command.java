package duke;

import java.util.function.Consumer;

public class Command {
    private final String name;
    private final Consumer<Parser> executor;

    public void run(Parser args) {
        this.executor.accept(args);
    }
    
    public Command(String name, Consumer<Parser> executor) {
        this.name = name;
        this.executor = executor;
    }
}
