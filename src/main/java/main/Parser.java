package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;
import javafx.util.Pair;

import java.io.IOException;
import java.time.LocalDate;

/**
 * A parse class which makes sense of the input of user
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList){
        this.taskList = taskList;
    }

    public String output(Pair<Boolean,String> pair) {
        if(pair.getKey()) {
            try {
                Storage.writeToFile(taskList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pair.getValue();
    }

    public String processInput(String str){
        String[] splitStr = str.split("\\s+",2);
        switch (splitStr[0].toLowerCase()) {
            case "bye":
                System.exit(0);
                return Ui.bye();
            case "list":
                return taskList.printList();
            case "mark":
                return output(taskList.mark(Integer.parseInt(splitStr[1])-1));
            case "unmark":
                return output(taskList.unmark(Integer.parseInt(splitStr[1])-1));
            case "delete":
                return output(taskList.remove(Integer.parseInt(splitStr[1])-1));
            case "todo":
                return output(addTodo(splitStr));
            case "deadline":
                return output(addDeadline(splitStr));
            case "event":
                return output(addEvent(splitStr));
            case "find":
                return find(splitStr);
            default:
                return "huh? what did you say?";
        }
    }

    public Pair<Boolean, String> addTodo(String[] splitStr){
        if (splitStr.length == 2) {
            Task todo1 = new Todo(splitStr[1],false);
            taskList.addTask(todo1);
            return new Pair<>(true,Ui.success(todo1));
        } else {
            return new Pair<>(false,Ui.error());
        }
    }

    public Pair<Boolean, String> addDeadline(String[] splitStr){
        if (splitStr.length == 2) {
            String[] splitStrDeadline = splitStr[1].split("/by", 2);
            if (splitStrDeadline.length == 2) {
                Task deadline1 = new Deadline(splitStrDeadline[0],false, LocalDate.parse(splitStrDeadline[1].trim()));
                taskList.addTask(deadline1);
                return new Pair<>(true,Ui.success(deadline1));
            } else {
                return new Pair<>(false,Ui.deadlineError());

            }
        } else {
            return new Pair<>(false,Ui.error());

        }
    }

    public Pair<Boolean, String> addEvent(String[] splitStr){
        if (splitStr.length == 2) {
            String[] splitStrEvent = splitStr[1].split("/from|/to");
            if (splitStrEvent.length == 3) {
                Task event1 = new Event(splitStrEvent[0],false, LocalDate.parse(splitStrEvent[1].trim()), LocalDate.parse(splitStrEvent[2].trim()));
                taskList.getList().add(event1);
                return new Pair<>(true,Ui.success(event1));
            } else {
                return new Pair<>(false,Ui.eventError());
            }
        } else {
            return new Pair<>(false,Ui.error());
        }
    }

    public String find(String[] splitStr){
        if (splitStr.length == 2) {
            return taskList.printList(taskList.findList(splitStr[1]));
        } else {
            return Ui.error();
        }
    }
}
