import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.*;


public class Duke {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/data/duke.txt";

    //to save the tasks to the file
    private static void saveToFile(int size, Task[] tasks){
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (int i =0; i < size; i++){
                Task task = tasks[i];
                writer.println(task.toFileString());
            }
        } catch (IOException e){
            System.out.println("Could not save tasks to file" + e.getMessage());
        }
    }

    private static Task[] loadFromFile(){
        Task[] tasks = new Task[100];
        int index = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                //adding the task from the file to be read
                tasks[index] = Task.fromFileString(line);
                index++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found! Creating new Data File...");
            saveToFile(index, tasks);
        } catch (IOException e){
            System.out.println("Error loading file" + e.getMessage());
        }
        return tasks;

    }

    public static void main(String[] args) {
        String line = "------------------------------";
        String[] dateFormats = {"dd/MM/yyyy", "MMM dd yyyy"};
        Scanner obj = new Scanner(System.in);
        Task[] lst = loadFromFile();
        int i = 0;
        while(lst[i] != null){
            i++;
        }
        System.out.println("\n Hello! I'm Leo\n" +
                " What can I do for you?");
        System.out.println(line);
        while (obj.hasNextLine()) {
            String res = obj.nextLine();
            if (res.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            }

            String[] key = res.split(" ", 2);
            if(key.length <= 1 && !key[0].equals("list")){
                System.out.println("Say something valid please -_-");
                System.out.println(line);
                continue;
            }
            switch (key[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list!");
                    for (int j = 0; j < i; j++) {
                        int nr = j + 1;
                        System.out.println(nr +"." + lst[j].toString()+ ".");
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                case "mark":
                    for(Task task:lst){
                        if (task != null){
                            String str = task.description;
                            if (str.equals(key[1])) {
                                task.markAsDone();
                            }
                        }
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + key[1]);
                    break;

                case "unmark":
                    for(Task task:lst){
                        if(task != null) {
                            String str = task.description;
                            if (str.equals(key[1])) {
                                task.unMark();
                            }
                        }
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + key[1] );
                    break;
                case "todo":
                    Task item = new ToDo(key[1]);
                    lst[i] = item;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(item.toString());
                    i++;
                    saveToFile(i, lst);
                    System.out.println("Now you have " + i + " task(s) in your list!");
                    System.out.println(line);
                    break;
                case "deadline":
                    String[] by = key[1].split("/by ", 2);
                    String[] dateTime = by[1].split(" ", 2);
                    //LocalDate deadlineDate = LocalDate.parse(by[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    LocalDate deadlineDate = null;
                    for (String format : dateFormats) {
                        try {
                            deadlineDate = LocalDate.parse(by[1].trim(),
                                                        DateTimeFormatter.ofPattern(format));
                            break;
                        } catch (DateTimeParseException ignored) {
                        }
                    }
                    if(deadlineDate != null) {
                        Task dline = new Deadline(by[0], deadlineDate);
                        lst[i] = dline;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(dline.toString());
                        i++;
                        saveToFile(i, lst);
                        System.out.println("Now you have " + i + " task(s) in your list!");
                    } else {
                        System.out.println("Invalid date and time format -_-");
                    }
                    System.out.println(line);
                    break;
                //Event
                case "event":
                    String[] fromto = key[1].split("/", 3);

                    LocalDate fromDate = null;
                    LocalDate toDate = null;
                    for (String format : dateFormats) {
                        try {
                            fromDate = LocalDate.parse(fromto[1], DateTimeFormatter.ofPattern(format));
                            toDate = LocalDate.parse(fromto[2], DateTimeFormatter.ofPattern(format));
                            break;
                        } catch (DateTimeParseException ignored) {
                        }
                    }
                    if(toDate != null & fromDate!=null) {
                        Task e = new Event(fromto[0], fromDate, toDate);
                        lst[i] = e;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(e.toString());
                        i++;
                        saveToFile(i, lst);
                        System.out.println("Now you have " + i + " task(s) in your list!");
                        System.out.println(line);
                    } else {
                        System.out.println("Invalid date and time format -_-");
                    }
                    break;
                case "delete":
                    int toDelete = Integer.valueOf(key[1]) -1;
                    if(toDelete >=0 && toDelete< i){
                        Task taskToDelete = lst[toDelete];
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskToDelete.toString());
                        for(int p = toDelete; p< i; p++){
                            lst[p] = lst[p+1];

                        }
                        lst[i - 1] = null;
                        i--;
                        System.out.println("Now you have " + i + " task(s) in the list.");
                    } else {
                        System.out.println("That is an invalid task to delete sir??");
                    }
                    System.out.println(line);
                    break;

                default:
                    System.out.println("Sorry what??, I did not get that!");


            }

        }
    }
}
