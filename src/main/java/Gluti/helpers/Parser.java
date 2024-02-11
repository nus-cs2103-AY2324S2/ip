package Gluti.helpers;

import Gluti.utils.*;

import java.util.ArrayList;

/**
 * An Interface to interpret user input and call relevant functions
 * commands are called from UI to perform tasks (Acts as the parser and response)
 */
public class Parser {
    private FileStorage fstorage;
    protected Ui ui;
    /**
     * Initialises a Parser instance
     * @param fstorage loads and writes tasks from/to data
     */
    public Parser(FileStorage fstorage) {
        this.fstorage = fstorage;
        this.ui = new Ui();
    }

    /**
     * Parsers the user inputs and perform relevant tasks accordingly
     *
     * @param word The line read from user input
     * @return
     * @throws GlutiException Caught exceptions from Tasks subclasses
     */
    public String parse(String word) throws GlutiException {
        ArrayList<Task> storage = fstorage.readList();
        assert word != null : "command should not be empty";
        String function = word.split(" ")[0].toLowerCase();
            String[] input = word.split(" ");
            function = input[0].toLowerCase();
            switch (function) {
                case "bye":
                    break;
                case "list":
                    int num = 1;
                    if (!storage.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < storage.size(); i++) {
                            sb.append(i + 1).append(".").append(storage.get(i).toString());
                            if (i != storage.size() - 1) {
                                sb.append("\n");
                            }
                        }
                        return sb.toString();
                    } else {
                        return "List is Empty!";
                    }
                case "mark":
                    int index = Integer.parseInt(input[1]);
                    try {
                        storage.get(index - 1).setDone();
                        Task task = storage.get(index - 1);
                        return "Nice! I've marked this task as done:\n" + task.toString();
                    } catch (IndexOutOfBoundsException e) {
                        return "Make sure that you have selected the correct task!";
                    }
                case "unmark":
                    index = Integer.parseInt(input[1]);
                    try {
                        storage.get(index - 1).setunDone();
                        Task task = storage.get(index - 1);
                        return "OK, I've marked this task as not done yet:\n" + task.toString();
                    } catch (IndexOutOfBoundsException e) {
                        return "Make sure that you have selected the correct task!";
                    }
                case "todo":
                    String[] tempinput = word.split(" ", 2);
                    try {
                        assert tempinput.length > 2;
                        Todo toDo = new Todo(tempinput[1]);
                        storage.add(toDo);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Got it. I've added this task:\n").append(toDo).append("\n");
                        sb.append("Now you have ").append(storage.size()).append(" tasks in the list.");
                        return sb.toString();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return "Todo must have a description!";
                    }
                case "event":
                    String[] tempinpute = word.split(" ", 2);
                    try {
                        assert tempinpute.length == 2 ;
                        assert tempinpute[0].length() > 1 ;
                        String description = tempinpute[1].split("/from",2)[0];
                        String[] period = tempinpute[1].split("/from",2)[1].split("/to",2);
                        Event event = new Event(description, period);
                        storage.add(event);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Got it. I've added this task:\n").append(event);
                        sb.append("\n");
                        sb.append("Now you have ").append(storage.size()).append(" tasks in the list.");
                        return sb.toString();
                    } catch (IndexOutOfBoundsException e) {
                        return("Event must be in this format event <description> /from <date+time> /to <time>");
                    }
                case "deadline":
                    String[] tempinputd = word.split(" ", 2);//[1].split(" /by ", 2);
                    try {
                        assert tempinputd.length == 2;
                        assert tempinputd[0].length() > 3;
                        String description = tempinputd[1].split("/by", 2)[0];
                        String time = tempinputd[1].split("/by", 2)[1];
                        Deadline deadline = new Deadline(description, time);
                        StringBuilder sb = new StringBuilder();
                        storage.add(deadline);
                        sb.append("Got it. I've added this task:\n").append(deadline).append("\n");
                        sb.append("Now you have ").append(storage.size()).append(" tasks in the list.");
                        return sb.toString();
                    } catch (IndexOutOfBoundsException e) {
                        return "Deadline must be in this format <description> /by <date+time>";
                    }
                case "delete":
                    index = Integer.parseInt(input[1]);
                    try {
                        Task task = storage.get(index - 1);
                        storage.remove(index - 1);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Noted. I've removed this task:\n").append(
                                task.toString()).append("\n");
                        sb.append("Now you have ").append(storage.size()).append(" tasks in the list.");
                        return sb.toString();
                    } catch (IndexOutOfBoundsException e) {
                        return "Make sure that you have selected the correct task!";
                    }
                case "find":
                    ArrayList<Task> matchlist = new ArrayList<>();
                    String keyword = input[1];
                    for (Task x : storage) {
                        if (x.isMatch(keyword)){
                            matchlist.add(x);
                        }
                    }
                    if (matchlist.isEmpty()) {
                        return "No matching tasks!";
                    } else {
                        ui.sendMessage("Here are the matching tasks in your list:");
                        int count = 1;
                        StringBuilder sb = new StringBuilder();
                        for (Task y : matchlist) {
                                sb.append(count).append(".").append(y.toString());
                                sb.append("\n");
                        }
                        return sb.toString();
                    }
                default:
                    break;
            }
        //}
        fstorage.saveList(storage);
        return "";
    }
    };
