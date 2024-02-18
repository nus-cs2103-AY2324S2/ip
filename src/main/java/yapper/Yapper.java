package yapper;

import java.util.HashMap;
import java.util.Map;

import exception.YapperException;

/**
 * Main class for the Yapper program to run.
 */
public class Yapper {

    private final TaskList mainTasks;
    private final Ui ui;
    private final Parser parser;
    private final FileManager fm;


    public Yapper() {
        mainTasks = new TaskList();
        ui = new Ui(mainTasks);
        parser = new Parser(mainTasks, ui);
        fm = new FileManager(parser);
        fm.loadTasks();
    }

    /**
     * Enum for the different types of commands available for the user to use.
     */
    public enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find"),
        HELP("help");

        private static final Map<String, Command> COMMAND_MAP = new HashMap<>();
        private final String name;

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


    public String getResponse(String input) {
        try {
            return parser.parseCommand(input);
        } catch (YapperException y) {
            return y.getMessage();
        } catch (Exception e) {
            return "unexpected exception in the getResponse function: \n"
                    + e.getClass().getSimpleName() + " " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        Yapper yapper = new Yapper();
    }
}
