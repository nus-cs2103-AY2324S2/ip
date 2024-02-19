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
        } else if (str.equals("meow")) {
            return list.meow();
        } else if (identifier.equals("mark")) {
           return list.markCase(tokens);
        } else if (identifier.equals("unmark")) {
            return list.unmarkCase(tokens);
        } else if (identifier.equals("event")) {
            return list.eventCase(str);
        } else if (identifier.equals("deadline")) {
            return list.deadlineCase(str);
        } else if (identifier.equals("todo")) {
            return list.todoCase(str);
        } else if (identifier.equals("delete")) {
            return list.removeCase(tokens);
        } else if(identifier.equals("find")) {
            return list.findCase(str);
        } else if(identifier.equals("priority")){
            return list.priorityCase(tokens);
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";

        }

    }
}
