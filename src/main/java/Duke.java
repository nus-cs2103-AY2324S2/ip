import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static void botUnknownFormat(int i) {
        System.out.println("Oops, I don't understand the file format");
        i = i + 1;
        System.out.println("Line " + i + " in the given file will be ignored");
    }
    private static ArrayList<Task> loadFile(File file) throws FileNotFoundException {
        ArrayList<Task> ls = new ArrayList<>();
        ArrayList<String> existing = new ArrayList<>();



        //Load the current content of the file
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String st = s.nextLine();
            existing.add(st);
        }

        //Turn content in the file into a list
        for (int i = 0; i < existing.size(); i++) {
            String tk = existing.get(i);
            String[] arr = tk.split("<>", 4);

            switch (arr[0]) {
                case "T":
                    try {
                        if (Integer.valueOf(arr[1]) == 0) {
                            //The task is not finished
                            Task t = new ToDo(arr[2]);
                            ls.add(t);
                        } else if (Integer.valueOf(arr[1]) == 1) {
                            //Task Finished
                            Task t = new ToDo(arr[2]);
                            t.mark();
                            ls.add(t);
                        } else {
                            botUnknownFormat(i);
                        }
                    } catch (Exception e) {
                        botUnknownFormat(i);
                    }
                    break;
                case "D":
                    try {
                        if (Integer.valueOf(arr[1]) == 0) {
                            //The ddl is not finished
                            Task t = new Deadline(arr[2], arr[3]);
                            ls.add(t);
                        } else if (Integer.valueOf(arr[1]) == 1) {
                            //DDL Finished
                            Task t = new Deadline(arr[2], arr[3]);
                            t.mark();
                            ls.add(t);
                        } else {
                            botUnknownFormat(i);
                            System.out.println("brk pt 1");
                        }
                    } catch (Exception e) {
                        botUnknownFormat(i);
                    }
                    break;
                case "E":
                    try {
                        if (Integer.valueOf(arr[1]) == 0) {
                            //The event is not finished
                            String[] temp = arr[3].split("to", 2);
                            String timeOfto = " to " + temp[1];
                            Task t = new Event(arr[2], temp[0], timeOfto);
                            ls.add(t);
                        } else if (Integer.valueOf(arr[2]) == 1) {
                            //Event Finished
                            String[] temp = arr[3].split(" to ", 2);
                            String timeOfto = "to" + temp[1];
                            Task t = new Event(arr[2], temp[0], timeOfto);
                            t.mark();
                            ls.add(t);
                        } else {
                            botUnknownFormat(i);
                        }
                    } catch (Exception e) {
                        botUnknownFormat(i);
                    }
                    break;
                default:
                    //Format unknown
                    botUnknownFormat(i);
                    break;
            }
        }

        return ls;
    }

    private static void saveFile(File file, ArrayList<Task> ls) throws IOException {
        FileWriter fw = new FileWriter(file);
        String separator = "<>";
        ArrayList<String> arr = new ArrayList<>();

        if (ls.size() == 0 ) {
            fw.write("");
        } else {
            for (Task task: ls) {
                if (task instanceof ToDo) {
                    String s;
                    if (!task.getStatus()) {
                        s = "T" + separator + "0" + separator + task.getDescription();
                    } else {
                        s = "T" + separator + "1" + separator + task.getDescription();
                    }
                    arr.add(s);
                } else if (task instanceof Deadline) {
                    String s;
                    if (!task.getStatus()) {
                        s = "D" + separator + "0" + separator + task.getDescription() + separator + ((Deadline) task).getBy();
                    } else {
                        s = "D" + separator + "1" + separator + task.getDescription() + separator + ((Deadline) task).getBy();
                    }
                    arr.add(s);
                } else if (task instanceof Event) {
                    String s;
                    if (!task.getStatus()) {
                        s = "E" + separator + "0" + separator + task.getDescription() + separator + ((Event) task).getFromTo();
                    } else {
                        s = "E" + separator + "1" + separator + task.getDescription() + separator + ((Event) task).getFromTo();
                    }
                    arr.add(s);
                } else {
                    System.out.println("The task you enter is of a type undefined here.");
                }
            }

            for (String str: arr) {
                fw.write(str + System.lineSeparator());
            }
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        String sayHi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        String divider = "_____________________________________________";
        String markDone = "Good job! I've marked this task as done:";
        String markNotDone = "Okie, Rest well. Marked this task as not done yet: ";
        String added = "Got it. I've added this task:";
        String dkEmpty = "Oops! Sorry, I don't know what that means. Description is empty";
        ArrayList<Task> ls;

        String filePath = new File("").getAbsolutePath();
        filePath += "/duke.txt";
        File file = new File(filePath);
        boolean ifExist = file.exists();
        if (!ifExist) {
            file.createNewFile();
        }

        //Get the existing tasks in the file
        ls = loadFile(file);


        //Scanner for getting user input
        Scanner myCom = new Scanner(System.in);
        System.out.println(sayHi + divider);
        String command = myCom.nextLine();


        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here're the tasks in ur list:");
                int counter = 0;
                for (Task i : ls) {
                    counter++;
                    System.out.println(counter + ". " + i.toString());
                }
                System.out.println(divider);
                command = myCom.nextLine();
            } else if (command.startsWith("unmark")) {
                try {
                    int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                    Task tk = ls.get(taskNum - 1);

                    tk.unmark();
                    System.out.println(markNotDone+"\n"+ "  " + tk.toString());
                    System.out.println(divider);
                    command = myCom.nextLine();
                } catch (Exception exc) {
                    System.out.println("Plz tell me which task." + "\n" + divider);
                    command = myCom.nextLine();
                }

            } else if (command.startsWith("mark")) {
                try {
                    int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                    Task tk = ls.get(taskNum - 1);

                    tk.mark();
                    System.out.println(markDone+"\n"+ "  " + tk.toString());
                    System.out.println(divider);
                    command = myCom.nextLine();
                } catch (Exception exc) {
                    System.out.println("Plz tell me which task." + "\n" + divider);
                    command = myCom.nextLine();
                }

            } else if (command.startsWith("delete")) {
                try {
                    int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                    Task tk = ls.get(taskNum - 1);
                    System.out.println("Noted. Task Removed:"+"\n"+ "  " + tk.toString());
                    ls.remove(taskNum - 1);

                    System.out.println("Now u have " + ls.size() +
                            " tasks in the list." + "\n" + divider);
                    command = myCom.nextLine();
                } catch (Exception exc) {
                    System.out.println("Plz tell me which task." + "\n" + divider);
                    command = myCom.nextLine();
                }

            } else {
                String[] cmd = command.split(" ", 2);
                if (cmd[0].equals("todo")) {
                    try {
                        Task t = new ToDo(cmd[1]);
                        ls.add(t);
                        System.out.println("  " + added + "\n" +
                                "    " + t.toString() + "\n" +
                                "  Now u have " + ls.size() +
                                " tasks in the list." + "\n" + divider);
                        command = myCom.nextLine();
                    } catch (Exception exc) {
                        System.out.println(dkEmpty + "\n" + divider);
                        command = myCom.nextLine();
                    }
                }
                 else if (cmd[0].equals("deadline")) {
                     try {
                         String[] date = cmd[1].split("/by", 2);
                         try {
                             String d = date[1].trim();
                             Task t = new Deadline(date[0], d);
                             ls.add(t);
                             System.out.println("  " + added + "\n" +
                                     "    " + t.toString() + "\n" +
                                     "  Now u have " + ls.size() +
                                     " tasks in the list."+ "\n" + divider);
                             command = myCom.nextLine();
                         }
                         catch (Exception exc) {
                             System.out.println("Plz enter a date for the deadline using '/by'");
                             System.out.println("Also notice the format should be like this: yyyy-mm-dd'");
                             System.out.println(divider);
                             command = myCom.nextLine();
                         }
                     } catch (Exception exc) {
                         System.out.println(dkEmpty + "\n" + divider);
                         command = myCom.nextLine();
                     }
                } else if (cmd[0].equals("event")) {
                     try {
                         String[] date = cmd[1].split("/", 3);
                         try {
                             Task t = new Event(date[0], date[1], date[2]);
                             ls.add(t);
                             System.out.println("  " + added + "\n" +
                                     "    " + t.toString() + "\n" +
                                     "  Now u have " + ls.size() +
                                     " tasks in the list."+ "\n" + divider);
                             command = myCom.nextLine();
                         } catch (Exception exc) {
                             System.out.println("Plz enter a date for the event using '/from' and '/to'\n" + divider);
                             command = myCom.nextLine();
                         }
                     } catch (Exception exc) {
                         System.out.println(dkEmpty + "\n" + divider);
                         command = myCom.nextLine();
                     }
                } else {
                    System.out.println("Oops! I don't understand the instruction." + "\n" + divider);
                    command = myCom.nextLine();
                }

            }
        }

        if (command.equals("bye")) {
            String sayBye = "Bye Bye. See u later!\n";
            System.out.println(sayBye + divider);

            //Update the file
            saveFile(file, ls);
        }

    }
}
