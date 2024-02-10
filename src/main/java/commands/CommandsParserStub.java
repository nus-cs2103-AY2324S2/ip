package commands;

import exceptions.RyanGoslingException;
import utilities.ResponseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsParserStub {
    public int parseCommandsOriginal(String task) throws RyanGoslingException {
        String[] taskSplit = task.split(" ");
        if (task.equals(String.valueOf(CommandsEnum.bye))) {
            ResponseHandler.bye();
            return 1;
        } else if (task.equals(String.valueOf(CommandsEnum.list))) {
            //taskList.printList();
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || taskSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
            //All items to be 0-index referenced other than user input.
            //taskList.changeStatusOfItem(taskSplit[0], Integer.parseInt(taskSplit[1])-1);
            //taskList.writeToFile(taskLoader);
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            //Idea from chatGPT
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                //taskList.add(new Todo(matcher.group(1)));
                //taskList.writeToFile(taskLoader);
                System.out.println("task");
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                //taskList.add(new Deadline(matcher.group(1), matcher.group(2)));
                //taskList.writeToFile(taskLoader);
                System.out.println("task");
            } else {
                throw new RyanGoslingException("Incomplete deadline command, "
                        + "deadline <event> /by <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            //System.out.println(task);
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                // Retrieve matched groups
                //System.out.println(matcher.group(3));
                //taskList.add(new Events(matcher.group(1), matcher.group(2), matcher.group(3)));
                //taskList.writeToFile(taskLoader);
                System.out.println("task");
            } else {
                throw new RyanGoslingException("Incomplete event command, "
                        + "event <event> /from <time> /to <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.delete))) {
            //taskList.removeIndex(Integer.parseInt(taskSplit[1])-1);
            //taskList.writeToFile(taskLoader);
        } else {
            throw new RyanGoslingException("I was created in a few hours so "
                    + "I don't know what that means :(");
        }
        return 0;
    }

    public String parseCommandsReturnString(String task) throws RyanGoslingException {
        assert task != null : "Task should never be null!";
        //We only have tests for tasks that return a string to be printed.
        assert !(task.equals("list")
                         || task.equals("mark")
                         || task.equals("unmark")
                         || task.equals("delete"))
                : "Task input for stub should not be, there is no test for this!" + task;
        String[] taskSplit = task.split(" ");
        if (task.equals(String.valueOf(CommandsEnum.bye))) {
            return "1";
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                return task;
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                return task;
            } else {
                throw new RyanGoslingException("Incomplete deadline command, "
                        + "deadline <event> /by <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                return task;
            } else {
                throw new RyanGoslingException("Incomplete event command, "
                        + "event <event> /from <time> /to <time>");
            }
        } else {
            throw new RyanGoslingException("I was created in a few hours so "
                    + "I don't know what that means :(");
        }
    }

    public static void main(String[] args) throws RyanGoslingException {
        System.out.println(new CommandsParserStub().parseCommandsReturnString("todo"));
    }
}
