import java.util.HashMap;
import java.util.function.Consumer;

public class Command {
    private final static HashMap<String, Command> commandMap = new HashMap<>();
    private final String name;
    private final Consumer<String[]> executor;
    
    public static void add(String name, Consumer<String[]> executor) {
        commandMap.put(name, new Command(name, executor));
    }
    
    public static boolean has(String name) {
        return commandMap.containsKey(name);
    }
    public static void run(String[] args) {
        commandMap.get(args[0]).executor.accept(args);
    }
    
    private Command(String name, Consumer<String[]> executor) {
        this.name = name;
        this.executor = executor;
    }
}
