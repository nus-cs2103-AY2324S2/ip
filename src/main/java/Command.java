import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public enum Command {
    List, Mark, Unmark, Delete, Todo, Deadline, Event, Bye, Invalid;

    private static List<Task> listOfTasks = new ArrayList<>();
    private static String splitLine = "____________________________________________________________";
    private static String exitString = "Bye. Hope to see you again soon!";

    public static final boolean handleCommand(Command command, String detail){
        boolean isDone = false;
        try {
            switch (command){
                case List:
                    if (listOfTasks.size() == 0){
                        throw new ToothlessException("Human list is empty like my tummy right now :/");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listOfTasks.size(); i++) {
                        Task t = listOfTasks.get(i);
                        printTaskState(t, i);
                    }
                    break;
                case Mark:{
                    int taskIndex = getTaskIndex(detail);
                    if (taskIndex >= listOfTasks.size() || taskIndex < 0 || detail.equals("")){
                        throw new ToothlessException("Human trying to mark nothing ^O^. Foolish");
                    }
                    Task t = listOfTasks.get(taskIndex);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    printTaskState(t, taskIndex);
                    break;
                }
                case Unmark:{
                    int taskIndex = getTaskIndex(detail);
                    if (taskIndex >= listOfTasks.size() || taskIndex < 0 || detail.equals("")){
                        throw new ToothlessException("Human trying to unmark nothing ^O^. Silly");
                    }
                    Task t = listOfTasks.get(taskIndex);
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    printTaskState(t, taskIndex);
                    break;
                }
                case Delete:{
                    int taskIndex = getTaskIndex(detail);
                    if (taskIndex >= listOfTasks.size() || taskIndex < 0 || detail.equals("")){
                        throw new ToothlessException("Human trying to delete nothing ^O^. Absurd");
                    }
                    Task t = listOfTasks.get(taskIndex);
                    listOfTasks.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    printTaskState(t, taskIndex);
                    System.out.format("Now you have %d tasks in the list.\n", listOfTasks.size());
                    break;
                }
                case Todo:
                case Deadline:
                case Event:{
                    Task t = createTask(command, detail);
                    listOfTasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    printTaskState(t, listOfTasks.size() - 1);
                    System.out.format("Now you have %d tasks in the list.\n", listOfTasks.size());
                    break;
                }
                case Bye:
                    isDone = true;
                    System.out.println(exitString);
                    writeTasks();
                    break;
                case Invalid:
                    throw new ToothlessException("Me dragon, no understand this action :P");
            }
        } catch (ToothlessException e){
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e){
            System.out.println("Type in date and time in this format: yyyy-mm-ddTHh:mm");
        } finally {
            System.out.println(splitLine);
            return isDone;
        }
    }

    public static final Task createTask(Command c, String detail) throws ToothlessException, DateTimeParseException{
        if(detail.equals("")){
            throw new ToothlessException("Human task no name :(");
        }
        Task newTask;
        switch (c){
            case Todo:{
                newTask = new Todo(detail);
                break;
            }
            case Deadline:{
                int dateIndex = detail.indexOf("/by");
                if (dateIndex == -1){
                    throw new ToothlessException("Human deadline no deadline @_@");
                }
                String description = detail.substring(0, dateIndex - 1);
                String date = detail.substring(dateIndex + 4);
                newTask = new Deadline(description, date);
                break;
            }
            case Event: {
                int date1Index = detail.indexOf("/from");
                if (date1Index == -1){
                    throw new ToothlessException("Human event no start date D:");
                }
                String description = detail.substring(0, date1Index - 1);
                detail = detail.substring(date1Index + 6);
                int date2Index = detail.indexOf("/to");
                if (date2Index == -1){
                    throw new ToothlessException("Human event no end date D:");
                }
                String startDate = detail.substring(0, date2Index - 1);
                String endDate = detail.substring(date2Index + 4);
                newTask = new Event(description, startDate, endDate);
            }
            default:
                throw new ToothlessException("Can't create task!");
        }
        return newTask;
    }

    public static void loadTasks(String filepath) throws FileNotFoundException, ToothlessException{
        File file = new File(filepath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            Task task;
            String[] storedTask = sc.nextLine().split(" \\| ");
            switch (storedTask[0]){
                case "T":
                    task = new Todo(storedTask[2], storedTask[1].equals("1"));
                    break;
                case "D":
                    task = new Deadline(storedTask[2], storedTask[3], storedTask[1].equals("1"));
                    break;
                case "E":
                    task = new Event(storedTask[2], storedTask[3], storedTask[4], storedTask[1].equals("1"));
                    break;
                default:
                    throw new ToothlessException("File corrupted O_O. Try again later.");
            }
            listOfTasks.add(task);
        }
    }

    public static void writeTasks(){
        try {
            new File("./data/toothless.txt").getParentFile().mkdirs();
            FileWriter writer = new FileWriter("./data/toothless.txt");
            for (Task task : listOfTasks){
                writer.write(task.toWrite() + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e){
            System.err.println("Unable to find task list :(");
        } catch (IOException e){
            System.err.println("Unable to save task :(");
        }
    }

    public static int getTaskIndex(String detail) throws ToothlessException{
        try {
            int taskIndex = Integer.valueOf(detail);
            return taskIndex - 1;
        } catch (NumberFormatException e){
            throw new ToothlessException("Number put is not number.\nPlease put real number ._.");
        }
    }

    public static void printTaskState(Task task, int index){
        System.out.format("%d. ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task + "\n", index + 1);
    }
}
