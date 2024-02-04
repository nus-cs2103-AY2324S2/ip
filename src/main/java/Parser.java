import java.util.ArrayList;

public class Parser {

    public Parser() {}

    public void processLine(String original, ArrayList<Task> list, Ui ui) throws DukeException {
        String[] inputParts = original.split("\\s+");

        //handle mark || unmark
        if (inputParts.length == 2 && (inputParts[0].equals("mark") || inputParts[0].equals("unmark"))) {
            int inputInt = Integer.parseInt(inputParts[1]);
            System.out.println(list.get(inputInt - 1).toggle());
        } else if (original.equals("list")) {
            //handle "list"
            ui.showList(list);
        } else if (inputParts[0].equals("todo")) {
            //handle "todoo"
            String description = original.replace("todo", "");
            if (description.isEmpty()) {
                throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
            }
            Task task = new ToDo(description);
            System.out.print(Duke.addMessage(task));
        } else if (inputParts[0].equals("deadline")) {
            //handle "deadline"
            String[] parts = original.replace("deadline", "").split(" /");
            Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
            System.out.print(Duke.addMessage(task));
        } else if (inputParts[0].equals("event")) {
            //handle event
            String[] parts = original.replace("event", "").split(" /");
            Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
            System.out.print(Duke.addMessage(task));
        } else if (inputParts[0].equals("delete")) {
            //handle delete
            int inputInt = Integer.parseInt(inputParts[1]);
            System.out.println(Duke.deleteMessage(inputInt));
        } else {
            throw new DukeException("harh what u talking sia walao");
        }

    }
}


