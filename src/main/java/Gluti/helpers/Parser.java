package Gluti.helpers;

import Gluti.utils.*;

import java.util.ArrayList;

public class Parser {
    private FileStorage fstorage;
    private boolean isExit;
    public Parser(FileStorage fstorage) {
        this.fstorage = fstorage;
        this.isExit = false;
    }

    public boolean isLooping() {
        return this.isExit;
    }
    public void parse(String word) throws GlutiException {
        ArrayList<Task> storage = fstorage.readList();
        String function = word.split(" ")[0].toLowerCase();
        //while(!function.equals("bye")) {
            String[] input = word.split(" ");
            function = input[0].toLowerCase();
            switch (function) {
                case "bye":
                    this.isExit = true;
                    break;
                case "list":
                    int num = 1;
                    if (!storage.isEmpty()) {
                        for (Task x : storage) {
                            System.out.println(num++ + "." + x.toString());
                        }
                    } else {
                        System.out.println("List is Empty!");
                    }
                    break;
                case "mark":
                    int index = Integer.parseInt(input[1]);
                    try {
                        storage.get(index - 1).setDone();
                        Task task = storage.get(index - 1);
                        System.out.println("Nice! I've marked this task as done:\n" +
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
                        System.out.println("OK, I've marked this task as not done yet:\n" +
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
                        System.out.println("Got it. I've added this task:\n" +
                                toDo);
                        System.out.println("Now you have " + storage.size() + " tasks in the list.");
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
                        System.out.println("Got it. I've added this task:\n" +
                                event);
                        System.out.println("Now you have " + storage.size() + " tasks in the list.");
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
                        System.out.println("Got it. I've added this task:\n" +
                                deadline);
                        System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new GlutiException("Deadline must be in this format <description> /by <date+time>");
                    }
                    break;
                case "delete":
                    index = Integer.parseInt(input[1]);
                    try {
                        Task task = storage.get(index - 1);
                        storage.remove(index - 1);
                        System.out.println("Noted. I've removed this task:\n" +
                                task.toString());
                        System.out.println("Now you have " + storage.size() + " tasks in the list.");
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
                        System.out.println("No matching tasks!");
                    } else {
                        System.out.println("Here are the matching tasks in your list:");
                        int count = 1;
                        for (Task y : matchlist) {
                            System.out.println(count++ + "." + y);
                        }
                    }
                    break;
                default:
                    break;
            }
        //}
        fstorage.saveList(storage);
    }
    };
