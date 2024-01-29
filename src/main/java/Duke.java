import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class    Duke {

    private static final String FILE_PATH = "./data/Duke.txt";
    private static final String INTRO = "____________________________________________________________\n"
            + "        Hello! I'm sibehupzcoder9000\n"
            + "        What you want sia\n"
            + "____________________________________________________________\n";
    private static final String OUTRO = "____________________________________________________________\n"
            + "        wow so ur gg to leave me...\n"
            + "____________________________________________________________\n";
    private static ArrayList<Task> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println(INTRO);

        //load file
        Duke.loadFileContents();

        String original = sc.nextLine();

        while (!original.equals("bye")) {
            try {
                String[] inputParts = original.split("\\s+");

                //handle mark || unmark
                if (inputParts.length == 2 && (inputParts[0].equals("mark") || inputParts[0].equals("unmark"))) {
                    int inputInt = Integer.parseInt(inputParts[1]);
                    System.out.println(list.get(inputInt - 1).toggle());
                } else if (original.equals("list")) {
                    //handle "list"
                    Duke.listOut();
                } else if (inputParts[0].equals("todo")) {
                    //handle "todoo"
                    String description = original.replace("todo", "");
                    if (description.isEmpty()) {
                        throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
                    }
                    Task task = new ToDo(description);
                    list.add(task);
                    System.out.print(Duke.addMessage(task));
                } else if (inputParts[0].equals("deadline")) {
                    //handle "deadline"
                    String[] parts = original.replace("deadline", "").split(" /");
                    Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
                    System.out.print(Duke.addMessage(task));
                } else if (inputParts[0].equals("event")) {
                    //handle event
                    String[] parts = original.replace("event", "").split(" /");
                    Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
                    System.out.print(Duke.addMessage(task));
                } else if (inputParts[0].equals("delete")) {
                    //handle delete
                    int inputInt = Integer.parseInt(inputParts[1]);
                    System.out.println(Duke.deleteMessage(inputInt));
                } else {
                    throw new DukeException("harh what u talking sia walao");
                }
            } catch (DukeException e) {
                String message = "____________________________________________________________\n"
                        + e.getMessage()
                        + "\n____________________________________________________________\n";
                System.out.println(message);
            }
            original = sc.nextLine();
            }

            Duke.writeToFile();
            System.out.print(OUTRO);
            sc.close();
        }

        public static void listOut() {
            StringBuilder listOutput = new StringBuilder("____________________________________________________________\n"
                            + " Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                listOutput.append(" ").append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
            }
            listOutput.append("____________________________________________________________\n");
            System.out.print(listOutput);
        }

        public static String deleteMessage(int i) {
            String output = "____________________________________________________________\n"
                    + " Noted. I've removed this task:\n"
                    + "   " + list.get(i - 1)
                    + "\n Now you have " + (list.size() - 1) + " tasks in the list.\n"
                    + "____________________________________________________________\n";
            list.remove(i - 1);
            return output;
        }

        public static String addMessage(Task task) {
            list.add(task);
            return "____________________________________________________________\n"
                    + " Got it. I've added this task:\n"
                    + "   " + task
                    + "\n Now you have " + (list.size()) + " tasks in the list.\n"
                    + "____________________________________________________________\n";
        }

        public static void loadFileContents() {
            File f = new File(FILE_PATH);
            try {
                if (!f.exists()) {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                }
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String string = s.nextLine();
                    String[] inputParts = string.split("\\s+");

                    if (inputParts[0].equals("[T]")) {
                        //handle "todoo"
                        String status = inputParts[1];
                        String description = inputParts[2];
                        Task task = new ToDo(description);
                        if (status.equals("[X]")) {
                            task.toggle();
                        }
                        list.add(task);
                    } else if (inputParts[0].equals("[D]")) {
                        //handle "deadline"
                        String status = inputParts[1];
                        String[] parts = string.replace("[D] [ ] ", "").replace("[D] [X] ", "").
                                split(" \\(by: ");
                        String description = parts[0];
                        String by = parts[1].replace(")", "");
                        Task task = new Deadline(description, by);
                        if (status.equals("[X]")) {
                            task.toggle();
                        }
                        list.add(task);
                    } else if (inputParts[0].equals("[E]")) {
                        //handle event
                        String status = inputParts[1];
                        String[] parts = string.replace("[E] [ ] ", "").replace("[E] [X] ", "").
                                split(" from: ");
                        String description = parts[0];
                        String from = parts[1].split(" to: ")[0];
                        String to = parts[1].split(" to: ")[1];

                        Task task = new Event(description, from, to);
                        if (status.equals("[X]")) {
                            task.toggle();
                        }
                        list.add(task);
                    }
                }
                s.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void writeToFile() throws IOException {
            try {
                FileWriter fw = new FileWriter(FILE_PATH);
                for (Task task : list) {
                    fw.write(task.toString() + "\n");
                }
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }


