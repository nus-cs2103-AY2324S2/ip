package bit;

import java.util.Scanner;


/**
 * Bit, the chatbot to handle all your tasks!
 */
public class Bit {

    private static final Ui UI = new Ui();
    private Tasklist tasklist;
    private Scanner scanner;
    private Parser parser;
    private Storage store;
    /**
     * Constructor for bit
     */
    public Bit() {
        tasklist = new Tasklist();
        scanner = new Scanner(System.in);
        store = new Storage();
        store.loadFile(tasklist);
        parser = new Parser();
    }
    public static void main(String[] args) {

        UI.greet();
        Tasklist tasklist = new Tasklist();
        Scanner scanner = new Scanner(System.in);
        Storage store = new Storage();
        store.loadFile(tasklist);
        store.cleanList();
        Parser parser = new Parser();

        while (true) {
            String input = scanner.nextLine();
            String s = parser.parse(input);
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                UI.listOut(tasklist);
            } else if (s.equals("mark")) {
                tasklist.mark(parser.getIndex());
            } else if (s.equals("unmark")) {
                tasklist.unmark(parser.getIndex());
            } else if (s.equals("delete")) {
                tasklist.delete(parser.getIndex());
            } else if (s.equals("find")) {
                UI.listHits(parser.getWord(), tasklist);
            } else if (s.equals("add")) {
                try {
                    tasklist.addTo(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        store.saveAll(tasklist);
        UI.sayBye();
    }
    public String getResponse(String input) {
        String addInput = input;
        input = parser.parse(input);
        if (input.equals("bye")) {
            System.exit(0);
        } else if (input.equals("list")) {
            return UI.listOut(tasklist);
        } else if (input.equals("mark")) {
            return tasklist.mark(parser.getIndex());
        } else if (input.equals("unmark")) {
            return tasklist.unmark(parser.getIndex());
        } else if (input.equals("delete")) {
            return tasklist.delete(parser.getIndex());
        } else if (input.equals("find")) {
            return UI.listHits(parser.getWord(), tasklist);
        } else if (input.equals("remind")) {
            return UI.listDueSoon(parser.getIndex(), tasklist);
        } else if (input.equals("add")) {
            try {
                return tasklist.addTo(addInput);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return "";
    }
}
