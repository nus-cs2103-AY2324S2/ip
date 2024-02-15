package gronk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Parser class.
 * Utility class to parse the user's messages and
 * execute them.
 */
public class Parser {
    private UserInterface userInterface;
    private TaskList taskList;

    /**
     * Constructor for Parser object.
     * Initializes a UserInterface to interact with
     * the user and a TaskList object to store data in.
     *
     * @param taskList TaskList object.
     */
    public Parser(TaskList taskList) {
        this.userInterface = new UserInterface();
        this.taskList = taskList;
    }

    private String parseDeadline(String message) {
        try {
            String[] words = message.substring(9).split(" /by ");
            Deadline newTask = Deadline.createDeadline(words[0], 0, words[1]);
            if (newTask == null) {
                throw new WrongTimeFormatException();
            } else {
                this.taskList.addTask(Deadline.createDeadline(words[0], 0, words[1]));
                return "\tDeadline added: " + words[0];
            }
        } catch (WrongTimeFormatException e) {
            this.userInterface.printMessage(e.toString());
        }
        return "";
    }

    private String parseEvent(String message) {
        try {
            String[] words = message.substring(6).split(" /from ");
            if (words[1].equals("")) {
                throw new EmptyDescException();
            }
            String[] words2 = words[1].split(" /to ");
            if (words2[0].equals("") || words2[1].equals("")) {
                throw new EmptyDescException();
            }
            Event newTask = Event.createEvent(words[0], 0, words2[0], words2[1]);
            if (newTask == null) {
                throw new WrongTimeFormatException();
            } else {
                this.taskList.addTask(Event.createEvent(words[0], 0, words2[0], words2[1]));
                return "\tEvent added: " + words[0];
            }
        } catch (EmptyDescException e) {
            this.userInterface.printMessage(e.toString());
        } catch (WrongTimeFormatException e) {
            this.userInterface.printMessage(e.toString());
        }
        return "";
    }

    private String parseDelete(String message) {
        try {
            ArrayList<String> splitMessage = new ArrayList<>(
                    Arrays.asList(message.split(" ")));
            int size = this.taskList.getSize();
            int index = Integer.parseInt(splitMessage.get(1)) - 1;
            if (size == 0) {
                throw new EmptyListException();
            } else if (index >= size || index < 0) {
                throw new NoSuchElementException();
            } else {
                String tempMessage = "\tItem: " + this.taskList.getTask(index).getDesc()
                        + " removed from list.";
                this.taskList.deleteTask(index);
                return tempMessage;
            }
        } catch (EmptyListException e) {
            this.userInterface.printMessage(e.toString());
        }
        return "";
    }

    private String parseMark(String message) {
        ArrayList<String> splitMessage = new ArrayList<>(
                Arrays.asList(message.split(" ")));
        int index = Integer.parseInt(splitMessage.get(1)) - 1;
        String returnMessage = this.taskList.getTask(index).statusMessage();
        this.taskList.getTask(index).flip();
        return returnMessage;
    }

    private String parseFind(String message) {
        String searchString = message.substring(5);
        TaskList matchedTasks = new TaskList();
        for (Task task: this.taskList.getAllTasks()) {
            if (task.getDesc().indexOf(searchString) != -1) {
                matchedTasks.addTask(task);
            }
        }
        return this.userInterface.returnAllTasks(matchedTasks);
    }

    private String parseTodo(String message) {
        this.taskList.addTask(new Todo(message.substring(5), 0));
        return "\tTask added: " + message.substring(5);
    }

    /**
     * Parser for user commands.
     * Contains all the logic to deal with user commands,
     * including error handling and initialization of new Task objects.
     *
     * @param message String containing user input.
     * @return String containing response message to user's input.
     */
    public String parse(String message) {
        try {
            if (message.equals("list")) {
                return this.userInterface.returnAllTasks(this.taskList);
            } else {
                ArrayList<String> splitMessage = new ArrayList<>(
                        Arrays.asList(message.split(" ")));
                if (splitMessage.size() == 1) {
                    String word = splitMessage.get(0);
                    if (word.equals("todo") || word.equals("deadline")
                            || word.equals("event") || word.equals("find")) {
                        throw new EmptyDescException();
                    } else {
                        throw new DukeException();
                    }
                } else if (splitMessage.get(0).equals("todo")) {
                    parseTodo(message);
                } else if (splitMessage.get(0).equals("deadline")) {
                    parseDeadline(message);
                } else if (splitMessage.get(0).equals("event")) {
                    parseEvent(message);
                } else if (splitMessage.get(0).equals("mark")) {
                    parseMark(message);
                } else if (splitMessage.get(0).equals("delete")) {
                    parseDelete(message);
                } else if (splitMessage.get(0).equals("find")) {
                    parseFind(message);
                } else {
                    throw new DukeException();
                }
            }
        } catch (DukeException e) {
            this.userInterface.printMessage(e.toString());
        } catch (EmptyDescException e) {
            this.userInterface.printMessage(e.toString());
        } catch (NoSuchElementException e) {
            this.userInterface.printMessage("\tThat item does not exist!");
        }
        return "";
    }
}
