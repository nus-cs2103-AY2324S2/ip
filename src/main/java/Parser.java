public class Parser {

    static TaskList list;
    static Storage storage;

    public Parser(TaskList list, Storage storage){
        this.list = list;
        this.storage = storage;
    }
    public static void parse(String str) throws DukeException{
        String[] tokens = str.split("\\s+");// split read string into individual components to read keywords
        String identifier = tokens[0];// store keywords

        if (str.equals("list")) {

            list.printlist();
        }// if keyword is list, open list

        else if (str.equals("bye")) {
            Ui.bye(storage);
        } else if (identifier.equals("mark")) {
            list.markcase(tokens);
        } else if (identifier.equals("unmark")) {
            int no = Integer.parseInt(tokens[1]) - 1;
            list.unmarkcase(tokens);
        } else if (identifier.equals("event")) {
            list.eventcase(str);
            list.addtask();

        } else if (identifier.equals("deadline")) {
            list.deadlinecase(str);
            list.addtask();
        } else if (identifier.equals("todo")) {
            list.todocase(str);
            list.addtask();
        } else if (identifier.equals("delete")) {
            list.removecase(tokens);

        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
