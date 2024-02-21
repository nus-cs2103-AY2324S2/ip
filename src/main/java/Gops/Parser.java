package Gops;

import java.io.File;
public class Parser {
    /**
     * Parses the user input and does the appropriate operation on the taskList and updates the hard disk
     * @param taskList list of tasks
     * @param txtFile file containing tasks
     */
    public String parse(String userReply, TaskList taskList, File txtFile) {
        assert userReply != null : "User reply missing";
        assert taskList != null: "Task list missing";
        assert txtFile != null : "Text file missing";

        if (userReply.equals("clear")) {
            taskList.clearList();
            Storage.writeToHardDisk(taskList, txtFile);
            return "List Cleared";
        } else if (userReply.contains("todo")) {
            String[] splitter = userReply.split(" ", 2);
            try {
                if (splitter.length == 1) {
                    throw new GopsException();
                }
                taskList.addNewTodo(splitter[1]);
                Storage.writeToHardDisk(taskList, txtFile);
                return taskList.printNewestTask();
            } catch (GopsException e) {
                return "Please follow the format for setting todo\ntodo [your-task-here]";
            }
        } else if (userReply.contains("list")) {
            if (taskList.isEmpty()) {
                return "No Tasks Left";
            } else {
                return taskList.listPrinter();
            }
        } else if (userReply.contains("deadline")) {
            String[] firstSplitter = userReply.split(" ", 2);
            try {
                if (firstSplitter.length == 1) {
                    throw new GopsException();
                }
                String[] secondSplitter = firstSplitter[1].split("/by", 2);
                taskList.addNewDeadline(secondSplitter[0].trim(), secondSplitter[1].trim());
                Storage.writeToHardDisk(taskList, txtFile);
                return taskList.printNewestTask();
            } catch (GopsException e) {
                return "Please follow the format for setting deadlines\ndeadline [your-task-here] /by [deadline-of-your-task]";
            }
        } else if (userReply.contains("event")) {
            String[] firstSplitter = userReply.split(" ", 2);
            try {
                if (firstSplitter.length == 1) {
                    throw new GopsException();
                }
                String[] secondSplitter = firstSplitter[1].split("/from", 2);
                String[] thirdSplitter = secondSplitter[1].split("/to", 2);
                taskList.addNewEvent(secondSplitter[0], thirdSplitter[0], thirdSplitter[1]);
                Storage.writeToHardDisk(taskList, txtFile);
                return taskList.printNewestTask();
            } catch (GopsException e) {
                return "Please follow the format for setting events\nevent [your-task-here] /from [start-time-of-your-task] /by [end-time-of-your-task]";
            }
        } else if (userReply.contains("unmark")) {
            try {
                if (userReply.length() == 6) {
                    throw new GopsException();
                }
                int toDoListIndex = Integer.parseInt(userReply.substring(userReply.length() - 1)) - 1;
                taskList.getTask(toDoListIndex).isDone = false;
                Storage.writeToHardDisk(taskList, txtFile);
                return taskList.printTaskAsNotMarked(toDoListIndex);
            } catch (GopsException e) {
                return "Please follow the format for unmarking tasks\nunmark [task-number]";
            }
        } else if (userReply.contains("mark")) {
            try {
                if (userReply.length() == 4) {
                    throw new GopsException();
                }
                int toDoListIndex = Integer.parseInt(userReply.substring(userReply.length() - 1)) - 1;
                taskList.changeTaskStatus(toDoListIndex, true);
                Storage.writeToHardDisk(taskList, txtFile);
                return taskList.printTaskAsMarked(toDoListIndex);
            } catch (GopsException e) {
                return "Please follow the format for marking tasks as done\nmark [task-number]";
            }
        } else if (userReply.contains("delete")) {
            try {
                if (userReply.length() == 6) {
                    throw new GopsException();
                }
                String[] splitter = userReply.split(" ", 2);
                int listIndex = Integer.parseInt(splitter[1]);
                if (listIndex > taskList.size()) {
                    throw new GopsException();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("I've deleted this task\n");
                sb.append("------------------------------------------------------------------\n");
                sb.append(taskList.stringPrintTask(listIndex - 1));
                sb.append("\n------------------------------------------------------------------\n");
                taskList.removeTask(listIndex - 1);
                Storage.writeToHardDisk(taskList, txtFile);
                sb.append(taskList.listPrinter());
                return sb.toString();
            } catch (GopsException e) {
                return "Please follow the format for deleting tasks\ndelete [task-number]";
            }
        } else if (userReply.contains("find")) {
            String[] splitter = userReply.split(" ", 2);
            return taskList.findInList(splitter[1]).listPrinter();
        } else if (userReply.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            return "Please choose from the available prompts\n[todo/deadline/event/mark/unmark/list/bye]";
        }
    }
}
