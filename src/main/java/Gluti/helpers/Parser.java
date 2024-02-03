package Gluti.helpers;

import Gluti.utils.*;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * An Interface to interpret user input and call relevant functions
 * commands are called from UI to perform tasks (Acts as the parser and response)
 */
public class Parser {
    private FileStorage fstorage;
    private boolean isExit;
    private Consumer<String> uiMessageCallback;
    /**
     * Initialises a Parser instance
     * @param fstorage loads and writes tasks from/to data
     */
    public Parser(FileStorage fstorage, Consumer<String> uiMessageCallback) {
        this.fstorage = fstorage;
        this.isExit = false;
        this.uiMessageCallback = uiMessageCallback;
    }

    /**
     * Returns the current status of the program
     * @return isExit to allow UI to know if the program is still running for it to print the exit message
     */
    public boolean isLooping() {
        return this.isExit;
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
        String function = word.split(" ")[0].toLowerCase();
            String[] input = word.split(" ");
            function = input[0].toLowerCase();
            switch (function) {
                case "bye":
                    String end = "Bye. Hope to see you again soon!";
                    this.isExit = true;
                    sendMessageToUI(end);
                    break;
                case "list":
                    int num = 1;
                    if (!storage.isEmpty()) {
                        for (Task x : storage) {
                            sendMessageToUI(num++ + "." + x.toString());
                        }
                    } else {
                        sendMessageToUI("List is Empty!");
                    }
                    break;
                case "mark":
                    int index = Integer.parseInt(input[1]);
                    try {
                        storage.get(index - 1).setDone();
                        Task task = storage.get(index - 1);
                        sendMessageToUI("Nice! I've marked this task as done:\n" +
                                task.toString());
                    } catch (IndexOutOfBoundsException e) {
                        throw new GlutiException("Make sure that you have selected the correct task!");
                    }
                    break;
                case "unmark":
                    index = Integer.parseInt(input[1]);
                    try {
                        storage.get(index - 1).setunDone();
                        Task task = storage.get(index - 1);
                        sendMessageToUI("OK, I've marked this task as not done yet:\n" +
                                task.toString());
                    } catch (IndexOutOfBoundsException e) {
                        throw new GlutiException("Make sure that you have selected the correct task!");
                    }
                    break;
                case "todo":
                    String[] tempinput = word.split(" ", 2);
                    try {
                        assert tempinput.length > 2;
                        Todo toDo = new Todo(tempinput[1]);
                        storage.add(toDo);
                        sendMessageToUI("Got it. I've added this task:\n" +
                                toDo);
                        sendMessageToUI("Now you have " + storage.size() + " tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e){
                        throw new GlutiException("Todo must have a description!");
                    }
                    break;
                case "event":
                    String[] tempinpute = word.split(" ", 2);
                    try {
                        assert tempinpute.length == 2 ;
                        assert tempinpute[0].length() > 1 ;
                        String description = tempinpute[1].split("/from",2)[0];
                        String[] period = tempinpute[1].split("/from",2)[1].split("/to",2);
                        Event event = new Event(description, period);
                        storage.add(event);
                        sendMessageToUI("Got it. I've added this task:\n" +
                                event);
                        sendMessageToUI("Now you have " + storage.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new GlutiException("Event must be in this format event <description> /from <date+time> /to <time>");
                    }

                    break;
                case "deadline":
                    String[] tempinputd = word.split(" ", 2);//[1].split(" /by ", 2);
                    try {
                        assert tempinputd.length == 2;
                        assert tempinputd[0].length() > 3;
                        String description = tempinputd[1].split("/by", 2)[0];
                        String time = tempinputd[1].split("/by", 2)[1];
                        Deadline deadline = new Deadline(description, time);
                        storage.add(deadline);
                        sendMessageToUI("Got it. I've added this task:\n" +
                                deadline);
                        sendMessageToUI("Now you have " + storage.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new GlutiException("Deadline must be in this format <description> /by <date+time>");
                    }
                    break;
                case "delete":
                    index = Integer.parseInt(input[1]);
                    try {
                        Task task = storage.get(index - 1);
                        storage.remove(index - 1);
                        sendMessageToUI("Noted. I've removed this task:\n" +
                                task.toString());
                        sendMessageToUI("Now you have " + storage.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new GlutiException("Make sure that you have selected the correct task!");
                    }
                    break;
                case "find":
                    ArrayList<Task> matchlist = new ArrayList<>();
                    String keyword = input[1];
                    for (Task x : storage) {
                        if (x.isMatch(keyword)){
                            matchlist.add(x);
                        }
                    }
                    if (matchlist.isEmpty()) {
                        sendMessageToUI("No matching tasks!");
                    } else {
                        sendMessageToUI("Here are the matching tasks in your list:");
                        int count = 1;
                        for (Task y : matchlist) {
                            sendMessageToUI(count++ + "." + y);
                        }
                    }
                    break;
                default:
                    break;
            }
        //}
        fstorage.saveList(storage);
        return "";
    }

    private void sendMessageToUI(String message) {
        if (uiMessageCallback != null) {
            uiMessageCallback.accept(message);
        }
    }
    };
