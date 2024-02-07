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
    public String parse(String str) throws DukeException{
        String[] tokens = str.split("\\s+");// split read string into individual components to read keywords
        String identifier = tokens[0];// store keywords

        if (str.equals("list")) {

            return list.printList();
        } else if (str.equals("bye")) {
            return list.bye();
            //System.exit(0);
        } else if (identifier.equals("mark")) {
           return list.markCase(tokens);
        } else if (identifier.equals("unmark")) {
            return list.unmarkCase(tokens);
        } else if (identifier.equals("event")) {
            return list.eventCase(str);
        } else if (identifier.equals("deadline")) {
            list.deadlineCase(str);
            return list.addTask();
        } else if (identifier.equals("todo")) {
            list.todoCase(str);
            return list.addTask();
        } else if (identifier.equals("delete")) {
            return list.removeCase(tokens);

        } else if(identifier.equals("find")) {
            return list.findCase(str);
        }else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            //throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        //return "Sorry! Bingus doesnt understand that yet! :(";
    }
}
