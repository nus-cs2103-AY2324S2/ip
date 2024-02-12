package rick;

import rick.RickException;
import rick.Storage;
import rick.tasks.Deadline;
import rick.tasks.Event;
import rick.tasks.Item;
import rick.tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Item> items;

    public TaskList () {
        this.items = new ArrayList<Item>();
    }
    public TaskList (ArrayList<Item> list) {
        this.items = list;
    }
    public String list() {
        StringBuilder string = new StringBuilder();
        String divider = "____________________________________________________________";
        string.append(divider + "\n");;
        for (int i = 0; i < items.size(); i++) {
            string.append((i+1) + ". " + items.get(i) + "\n");
        }
        string.append(divider);
        return string.toString();
    }

    public String addToList (String arg, Storage storage) throws RickException {
        Item new_item;
        String[] splited = arg.split("\\s+");
        int last = splited.length - 1;
        if (splited.length == 1) {
            throw new RickException("What THING do you want to do...");
        }
        try {
            if (splited[0].equals("todo")) {
                new_item = new ToDo(arg.substring(5), "[ ]");
                this.items.add(new_item);
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
                this.items.add(new_item);
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

                String from = arg.substring(i + 6, j-1);
                String to = arg.substring(j + 4);
                new_item = new Event(name, "[ ]", from, to);
                this.items.add(new_item);
            } else {
                throw new RickException("It seems that you are missing the space in your instruction. Homesick alien?");
            }
        } catch (RickException e) {
            throw e;
        } catch (Exception e1) {
            throw new RickException("ERROR: Congratulations! You have input a message that the developer did not expect. " +
                    "Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
        }
        storage.update();
        String output = "Got it. I've added this task:\n" +
                new_item +
                "\nNow you have " + items.size() + " tasks in the list.";
        return output;
    }

    public String mark(String arg, Storage storage) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(5))) {
            throw new RickException("You have to tell me the number to mark. Try 'mark 1'.");
        }
        int i = arg.charAt(5) - 49;
        if (i >= this.items.size()) {
            throw new RickException("rick.tasks.Item not found QAQ");
        } else {
            Item item = this.items.get(i);
            item.mark();
            storage.update();
            String output = "Nice! I've marked this task as done:\n"+ item;
            return output;
        }
    }

    public String unmark(String arg, Storage storage) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(7))) {
            throw new RickException("You have to tell me the number to unmark. Try 'unmark 1'.");
        }
        int i = arg.charAt(7) - 49;
        if (i >= this.items.size()) {
            throw new RickException("rick.tasks.Item not found QAQ");
        } else {
            Item item = this.items.get(i);
            item.unmark();
            storage.update();
            String output = "OK, I've marked this task as not done yet:\n"+ item;
            return output;
        }
    }

    public String delete(String arg, Storage storage) throws RickException {
        String[] splited = arg.split("\\s+");
        if (splited.length != 2 || !Character.isDigit(arg.charAt(7))) {
            throw new RickException("You have to tell me the number to delete. Try 'delete 1'.");
        }
        int i = arg.charAt(7) - 49;
        try {
            Item item = this.items.remove(i);
            storage.update();
            String output = "Noted. I've removed this task:\n" +
                    item +
                    "\nNow you have " + this.items.size() + " tasks in the list.";
            return(output);
        } catch (Exception e) {
            throw new RickException("Index wrong lah! :(");
        }
    }
}
