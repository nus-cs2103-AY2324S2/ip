package gronk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Parser {
    private UserInterface userInterface;
    private TaskList taskList;
    public Parser(TaskList taskList) {
        this.userInterface = new UserInterface();
        this.taskList = taskList;
    }

    public String parse(String message) {
        try {
            if (message.equals("list")) {
                return this.userInterface.returnAllTasks(this.taskList);
            } else {
                ArrayList<String> splitMessage = new ArrayList<>(
                        Arrays.asList(message.split(" ")));
                int messageLength = splitMessage.size();
                if (messageLength == 1) {
                    String word = splitMessage.get(0);
                    if (word.equals("todo") || word.equals("deadline") || word.equals("event")) {
                        throw new EmptyDescException();
                    } else {
                        throw new DukeException();
                    }
                } else if (splitMessage.get(0).equals("todo")) {
                    this.taskList.addTask(new Todo(message.substring(5), 0));
                    return "\tTask added: " + message.substring(5);
                } else if (splitMessage.get(0).equals("deadline")) {
                    String[] words = message.substring(9).split(" /by ");
                    Deadline newTask = Deadline.createDeadline(words[0], 0, words[1]);
                    if (newTask == null) {
                        throw new WrongTimeFormatException();
                    } else {
                        this.taskList.addTask(Deadline.createDeadline(words[0], 0, words[1]));
                        return "\tDeadline added: " + words[0];
                    }
                } else if (splitMessage.get(0).equals("event")) {
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
                } else if (splitMessage.get(0).equals("mark")) {
                    int index = Integer.parseInt(splitMessage.get(1)) - 1;
                    String returnMessage = this.taskList.getTask(index).statusMessage();
                    this.taskList.getTask(index).flip();
                    return returnMessage;
                } else if (splitMessage.get(0).equals("delete")) {
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
                } else {
                    throw new DukeException();
                }
            }
        } catch (DukeException e) {
            this.userInterface.printMessage(e.toString());
        } catch (EmptyDescException e) {
            this.userInterface.printMessage(e.toString());
        } catch (EmptyListException e) {
            this.userInterface.printMessage(e.toString());
        } catch (WrongTimeFormatException e) {
            this.userInterface.printMessage(e.toString());
        } catch (NoSuchElementException e) {
            this.userInterface.printMessage("\tThat item does not exist!");
        }
        return "";
    }
}
