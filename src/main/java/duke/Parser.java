package duke;

import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Deadline;
import tasks.ToDo;

public class Parser {

    public Parser() {}

    public void processLine(String original, TaskList list, Ui ui) throws DukeException {
        String[] inputParts = original.split("\\s+");

        //handle mark || unmark
        if (inputParts.length == 2 && (inputParts[0].equals("mark") || inputParts[0].equals("unmark"))) {
            int inputInt = Integer.parseInt(inputParts[1]);
            System.out.println(list.getTask(inputInt - 1).toggle());
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
            list.addTask(task);
            ui.addMessage(task, list);
        } else if (inputParts[0].equals("deadline")) {
            //handle "deadline"
            String[] parts = original.replace("deadline", "").split(" /");
            Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
            list.addTask(task);
            ui.addMessage(task, list);
        } else if (inputParts[0].equals("event")) {
            //handle event
            String[] parts = original.replace("event", "").split(" /");
            Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
            list.addTask(task);
            ui.addMessage(task, list);
        } else if (inputParts[0].equals("delete")) {
            //handle delete
            int inputInt = Integer.parseInt(inputParts[1]);
            list.deleteTask(inputInt);
            ui.deleteMessage(inputInt, list);
        } else {
            throw new DukeException("harh what u talking sia walao");
        }

    }
}


