package bit;

import  java.util.Scanner;



public class Bit {

    private static final Ui UI = new Ui();

    public static void main(String[] args) {

        UI.greet();
        Tasklist tasklist = new Tasklist();
        Scanner scanner = new Scanner(System.in);
        Storage store = new Storage();
        store.loadFile(tasklist);
        store.cleanList();
        Parser parser = new Parser();

        while(true) {
            UI.printLineBreak();
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
            } else if (s.equals("add")){
                try{
                    tasklist.addTo(input);
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            }



        }
        store.saveAll(tasklist);
        UI.sayBye();

    }










}
