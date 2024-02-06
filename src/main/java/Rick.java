import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Rick {
    public static ArrayList<Item> list = new ArrayList<>();
<<<<<<< HEAD

=======
    public static Path directoryPath = Paths.get("./data");
    public static Path filePath = Paths.get("./data/rick.txt");
    public static void loadFile() throws RickException {
        reply("Loading local data...");
        try {
            //If directory data does not exist, create it
            if (!Files.isDirectory(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            //If file duke.txt does not exist, create it
            if (!Files.exists(filePath)) {
                reply("Thank you for using Rick assistant! 어서 와, 리크은 처음이지?\n" +
                        "We are setting up your device for the first time!");
                Files.createFile(filePath);
            }
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line;
            while((line = reader.readLine()) != null) {
                String[] splited = line.split("\\|");
                //T|[ ]|name
                //D|[ ]|name|by
                //E|[ ]|name|from|to
                switch (splited[0]) {
                case ("T"):
                    if (splited.length != 3) {
                        reply(Integer.toString(splited.length));
                        reply(splited[2]);
                        throw new Exception("T length wrong");}
                    list.add(new ToDo(splited[2], splited[1]));
                    break;
                case ("D"):
                    if (splited.length != 4) {throw new Exception("D length wrong");}
                    list.add(new Deadline(splited[2], splited[1], splited[3]));
                    break;
                case ("E"):
                    if (splited.length != 5) {throw new Exception("E length wrong");}
                    list.add(new Event(splited[2], splited[1], splited[3], splited[4]));
                    break;
                default:
                    throw new Exception("starting letter wrong");
                }
            }
        } catch (Exception e) {
            throw new RickException(e.getMessage());
            //throw new RickException("There's something wrong with your local data... You might want to [check the file], " +
                    //"or [clear local data]");
        }
    }
    //TODO implement 'clear local data'
>>>>>>> branch-Level-7
    public static void main(String[] args) {
        try {
            loadFile();
        } catch (RickException e) {
            reply(e.getMessage());
            return;
        }
        hello();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    list();
                } else if (input.startsWith("mark")) {
                    mark(input);
                } else if (input.startsWith("unmark")) {
                    unmark(input);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    add_to_list(input);
                } else if (input.startsWith("delete")) {
                    delete(input);
                } else {
                    reply("I don't understand what you are saying... ㅜㅜ");
                }
            } catch (RickException e) {
                reply(e.getMessage());
            } catch (Exception e1) {
                reply(e1.getMessage());
//                reply("ERROR: Congratulations! You have input a message that the developer did not expect. Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
                return;
            }
        }
    }

    public static void update() throws RickException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Item i : list) {
                writer.write(i.store());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            throw new RickException("There's something wrong with saving the data ㅜㅜ");
        }
    }
    public static void hello() {
        String hello = "Hello! I'm Rick\n"+
                "Tell me about your plan !";
        reply(hello);
    }

    public static void reply(String arg) {
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n" + arg + "\n" + divider);
    }

    public static void exit(){
        String exit = "Bye. Hope to see you again soon !";
        reply(exit);
    }
    public static void echo(String args){
        reply(args);
    }

    public static void list() {
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println("\n" + divider);
    }

    public static void add_to_list(String arg) throws RickException {
        Item new_item;
        String[] splited = arg.split("\\s+");
        int last = splited.length - 1;
        if (splited.length == 1) {
            throw new RickException("What THING do you want to do...");
        }
        try {
            if (splited[0].equals("todo")) {
                new_item = new ToDo(arg.substring(5), "[ ]");
                list.add(new_item);
            } else if (splited[0].equals("deadline")) {
                if (!arg.contains(" /by ") || splited[last].equals("/by")) {
                    throw new RickException("When is it due? You haven't told me!");
                }
                if (splited[1].equals("/by")) {
                    throw new RickException("What's the deadline about?");
                }
                int i = arg.indexOf("/by");
                String ddl = arg.substring(i + 4);
                String name = arg.substring(9, i - 1);
                new_item = new Deadline(name, "[ ]", ddl);
                list.add(new_item);
            } else if (splited[0].equals("event")) {
                if (!arg.contains(" /from ") || !arg.contains(" /to ") || splited[last].equals("/to") || splited[last].equals("/from")) {
                    throw new RickException("WHEN is the event?");
                }
                if (splited[1].equals("/from") || splited[1].equals("/to")) {
                    throw new RickException("What event is this?");
                }
                int i = arg.indexOf("/from ");
                int j = arg.indexOf("/to ");
                String name = arg.substring(6, i-1);
<<<<<<< HEAD
                String from = i < j ? arg.substring(i + 6, j-1) : arg.substring(i + 6);
                String to = i < j ? arg.substring(j + 4) : arg.substring(j + 4, i - 1);
                new_item = new Event(name, from, to);
=======
                String from = arg.substring(i + 6, j-1);
                String to = arg.substring(j + 4);
                new_item = new Event(name, "[ ]", from, to);
>>>>>>> branch-Level-7
                list.add(new_item);
            } else {
                throw new RickException("It seems that you are missing the space in your instruction. Homesick alien?");
            }
        } catch (RickException e) {
            reply(e.getMessage());
            return;
        }
        String output = "Got it. I've added this task:\n" +
                new_item +
                "\nNow you have " + list.size() + " tasks in the list.";
        update();
        reply(output);
    }

    public static void mark(String arg) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(5))) {
            throw new RickException("You have to tell me the number to mark. Try 'mark 1'.");
        }
        int i = arg.charAt(5) - 49;
        if (i >= list.size()) {
            throw new RickException("Item not found QAQ");
        } else {
            Item item = list.get(i);
            item.mark();
            String output = "Nice! I've marked this task as done:\n"+ item;
            update();
            reply(output);
        }
    }

    public static void unmark(String arg) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(7))) {
            throw new RickException("You have to tell me the number to unmark. Try 'unmark 1'.");
        }
        int i = arg.charAt(7) - 49;
        if (i >= list.size()) {
            throw new RickException("Item not found QAQ");
        } else {
            Item item = list.get(i);
            item.unmark();
            String output = "OK, I've marked this task as not done yet:\n"+ item;
            update();
            reply(output);
        }
    }

    public static void delete(String arg) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(7))) {
            throw new RickException("You have to tell me the number to delete. Try 'delete 1'.");
        }
        int i = arg.charAt(7) - 49;
        try {
            Item item = list.remove(i - 1);
            String output = "Noted. I've removed this task:\n" +
                    item +
                    "\nNow you have " + list.size() + " tasks in the list.";
            update();
            reply(output);
        } catch (Exception e) {
            reply("Index wrong lah!");
        }
    }
}
