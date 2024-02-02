import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

public class gops {

    public static class Todo {
        protected String itemDescription;
        protected boolean todoStatus = false;
        public Todo(String itemDescription) {
            this.itemDescription = itemDescription;
        }
        public String doneOrNot() {
            if (todoStatus) {
                return "1";
            } else {
                return "0";
            }
        }
        public String stringPrinter() {
            return " T " + "| " + this.doneOrNot() + " | " + itemDescription;
        }
        public void Printer() {
            System.out.println(stringPrinter());
        }
    }

    public static class Deadline extends Todo {
        protected String todoDescription;
        protected boolean todoStatus = false;
        protected String dayTodoBy;
        public Deadline(String todoDescription, String dayTodoBy) {
            super(todoDescription);
            this.dayTodoBy = dayTodoBy;
        }

        @Override
        public String stringPrinter() {
            return " " + "D " + "| " + this.doneOrNot() + " | " + itemDescription + " | by:" + dayTodoBy;
        }

        @Override
        public void Printer() {
            System.out.println(stringPrinter());
        }

    }


    public static class Event extends Todo {
        protected String todoDescription;
        protected boolean todoStatus = false;
        protected String startBy;
        protected String endBy;
        public Event(String todoDescription, String startBy, String endBy) {
            super(todoDescription);
            this.startBy = startBy;
            this.endBy  = endBy;
        }

        @Override
        public String stringPrinter() {
            return " " + "E " + "| " + this.doneOrNot() + " | " + itemDescription + " | from: " + startBy + " | to: " + endBy + "";
        }

        @Override
        public void Printer() {
            System.out.println(stringPrinter());
        }
    }

    public static void writeToHardDisk(Todo[] taskList, int messageCount, File file) {
        BufferedWriter taskWriter = null;
        try {
            taskWriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < messageCount; i++) {
                taskWriter.write(taskList[i].stringPrinter());
                taskWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (taskWriter != null) {
                try {
                    taskWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Todo[] readFromHardDisk(File taskFile) {
        Todo[] taskList = new Todo[100];
        BufferedReader taskReader = null;
        int taskIndex = 0;

        try {
            taskReader = new BufferedReader(new FileReader(taskFile));
            String taskString;
            while ((taskString = taskReader.readLine()) != null) {
                String[] taskData = taskString.split("\\|");
                String taskType = taskData[0].trim();
                boolean status = taskData[1].trim().equals("1");
                String taskDescription = taskData[2].trim();

                if (taskType.equals("T")) {
                    taskList[taskIndex] = new Todo(taskDescription);
                    taskList[taskIndex].todoStatus = status;
                } else if (taskType.equals("D")) {
                    String dayToDoBy = taskData[3].trim().substring(3);
                    taskList[taskIndex] = new Deadline(taskDescription, dayToDoBy);
                    taskList[taskIndex].todoStatus = status;
                } else if (taskType.equals("E")) {
                    String startBy = taskData[3].trim().substring(6);
                    String endBy = taskData[4].trim().substring(4);
                    taskList[taskIndex] = new Event(taskDescription, startBy, endBy);
                    taskList[taskIndex].todoStatus = status;
                }
                taskIndex++;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return taskList;
    }

    public static void main(String[] args) {
        Scanner inputTaker = new Scanner(System.in);
        Todo[] toDoList = new Todo[100];
        int messageCount = 0;

        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File txtFile = new File(dataFolder,"gops.txt");
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            toDoList = readFromHardDisk(txtFile);
            for (Todo todo : toDoList) {
                if (todo != null) {
                    messageCount++;
                }
            }
        }

        System.out.println("Hello! I'm gops");
        System.out.println("What can I do for you?");

        String userReply = inputTaker.nextLine();
        while (userReply != null && !userReply.equals("bye")) {
            if (userReply.contains("todo")) {
                String[] splitter = userReply.split(" ", 2);
                try {
                    if (splitter.length == 1) {
                        throw new gopsException();
                    }
                    Todo todoObject = new Todo(splitter[1]);
                    toDoList[messageCount] = todoObject;
                    messageCount += 1;
                    System.out.println("Got it. I've added this task:");
                    todoObject.Printer();
                    writeToHardDisk(toDoList, messageCount, txtFile);
                    System.out.println("Now you have " + messageCount + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for setting todo\ntodo [your-task-here]");
                    userReply = inputTaker.nextLine();
                }
            }  else if (userReply.contains("deadline")) {
                String[] firstSplitter = userReply.split(" ", 2);
                try {
                    if (firstSplitter.length == 1) {
                        throw new gopsException();
                    }
                    String[] seocndSplitter = firstSplitter[1].split("/by", 2);
                    Deadline deadlineObject = new Deadline(seocndSplitter[0], seocndSplitter[1]);
                    toDoList[messageCount] = deadlineObject;
                    messageCount += 1;
                    System.out.println("Got it. I've added this task:");
                    deadlineObject.Printer();
                    writeToHardDisk(toDoList, messageCount, txtFile);
                    System.out.println("Now you have " + messageCount + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for setting deadlines\ndeadline [your-task-here] /by [deadline-of-your-task]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("event")) {
                String[] firstSplitter = userReply.split(" ", 2);
                try {
                    if (firstSplitter.length == 1) {
                        throw new gopsException();
                    }
                    String[] secondSplitter = firstSplitter[1].split("/from", 2);
                    String[] thirdSplitter = secondSplitter[1].split("/to", 2);
                    Event eventObject = new Event(secondSplitter[0], thirdSplitter[0], thirdSplitter[1]);
                    toDoList[messageCount] = eventObject;
                    messageCount += 1;
                    System.out.println("Got it. I've added this task:");
                    writeToHardDisk(toDoList, messageCount, txtFile);
                    eventObject.Printer();
                    System.out.println("Now you have " + messageCount + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for setting events\nevent [your-task-here] /from [start-time-of-your-task] /by [end-time-of-your-task]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("unmark")) {
                try {
                    if (userReply.length() == 6) {
                        throw new gopsException();
                    }
                    int toDoListIndex = Integer.parseInt(userReply.substring(userReply.length() - 1)) - 1;
                    toDoList[toDoListIndex].todoStatus = false;
                    System.out.println("OK! I've marked this task as not done yet:");
                    System.out.println("[" + toDoList[toDoListIndex].doneOrNot() + "] " + toDoList[toDoListIndex].itemDescription);
                    writeToHardDisk(toDoList, messageCount, txtFile);
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for unmarking tasks\nunmark [task-number]");
                    userReply = inputTaker.nextLine();
                }
            }
            else if (userReply.contains("mark")) {
                try {
                    if (userReply.length() == 4) {
                        throw new gopsException();
                    }
                    int toDoListIndex =  Integer.parseInt(userReply.substring(userReply.length() -1)) - 1;
                    toDoList[toDoListIndex].todoStatus = true;
                    System.out.println("Nice! I've marked this task as done:");
                    toDoList[toDoListIndex].Printer();
                    writeToHardDisk(toDoList, messageCount, txtFile);
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for marking tasks as done\nmark [task-number]");
                    userReply = inputTaker.nextLine();
                }
            }

            else if (userReply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < messageCount; i++) {
                    System.out.println(i + 1 + ")" + toDoList[i].stringPrinter());
                }
                userReply = inputTaker.nextLine();
            } else if (userReply.contains("delete")) {
                try {
                    if (userReply.length() == 6) {
                        throw new gopsException();
                    }
                    String[] splitter = userReply.split(" ", 2);
                    int listIndex = Integer.parseInt(splitter[1]);
                    if (listIndex > messageCount) {
                        throw new gopsException();
                    }
                    Todo deletedTask = toDoList[listIndex - 1];
                    toDoList[listIndex - 1] = null;
                    for (int i = listIndex; i < messageCount; i++) {
                        toDoList[i - 1] = toDoList[i];
                    }
                    System.out.println("I've deleted this task");
                    System.out.println(" " + deletedTask.stringPrinter());
                    messageCount -= 1;
                    writeToHardDisk(toDoList, messageCount, txtFile);
                    System.out.println("Here are the remaining " + messageCount + " task/s in your list:");
                    for (int i = 0; i < messageCount; i++) {
                        System.out.println(i + 1 + ")" + toDoList[i].stringPrinter());
                    }
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for deleting tasks\ndelete [task-number]");
                    userReply = inputTaker.nextLine();
                }
            } else {
                System.out.println("Please choose from the available prompts\n[todo/deadline/event/mark/unmark/list/bye]");
                userReply = inputTaker.nextLine();
            }
        }
        if (userReply.equals("bye")) {
            System.out.print("Bye. Hope to see you again soon!");
        }
    }
}