import java.util.HashMap;
import java.util.Map;

public class Yapper {
    public enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private final String name;
        private static final Map<String, Command> COMMAND_MAP = new HashMap<>();

        private Command(String name) {
            this.name = name;
        }

        static {
            for (Command c: values()) {
                COMMAND_MAP.put(c.name, c);
            }
        }

        public static Command valueOfCommandName(String name) {
            return COMMAND_MAP.get(name);
        }
    }

    public static void run() {
        Ui.hello();
        FileManager.loadTasks();
        while (!Ui.hasEnded()) {

            System.out.print(Ui.line());

            try {
                Parser.parseCommand();
            } catch (YapperException e) {
                System.out.println(e.getMessage());
            }

            System.out.print(Ui.line());
        }
    }

    public static void main(String[] args) {
        Yapper.run();
    }
}