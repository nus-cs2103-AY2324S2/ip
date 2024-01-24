import java.util.function.Consumer;

public class Command {
    private final String name;
    private final Consumer<String[]> executor;

    public void run(String[] args) {
        this.executor.accept(args);
    }
    
    public Command(String name, Consumer<String[]> executor) {
        this.name = name;
        this.executor = executor;
    }
}
