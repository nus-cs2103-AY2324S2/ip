package duke;

/**
 * class for the Parser for the mainframe.
 */
public class Parser {

    static TaskList list;

    /**
     * Creates a new Parser object.
     *
     * @param list  the task list.
     * @param storage the storage compartment of the mainframe
     * @throws DukeException If the file does not exist and cannot be created.
     */
    public Parser(TaskList list){
        this.list = list;

    }

    /**
     * parses a string into information that can be read by the tasklist
     *
     * @param str  string to be parsed
     *
     * @throws DukeException If the file does not exist and cannot be created.
     */
    public static void parse(String str) throws DukeException{
        String[] tokens = str.split("\\s+");// split read string into individual components to read keywords
        String identifier = tokens[0];// store keywords

        if (str.equals("list")) {

            list.printList();
        } else if (str.equals("bye")) {
            Ui.bye();
        } else if (identifier.equals("mark")) {
            list.markCase(tokens);
        } else if (identifier.equals("unmark")) {
            int no = Integer.parseInt(tokens[1]) - 1;
            list.unmarkCase(tokens);
        } else if (identifier.equals("event")) {
            list.eventCase(str);
            list.addTask();

        } else if (identifier.equals("deadline")) {
            list.deadlineCase(str);
            list.addTask();
        } else if (identifier.equals("todo")) {
            list.todoCase(str);
            list.addTask();
        } else if (identifier.equals("delete")) {
            list.removeCase(tokens);

        } else if(identifier.equals("find")) {
            list.findCase(str);
        }else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
