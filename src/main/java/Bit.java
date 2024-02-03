import java.util.ArrayList;
import  java.util.Scanner;



public class Bit {

    private static final Ui UI = new Ui();




    private static  String fileName = "./data/bit.txt";
    public static void main(String[] args) {

        UI.greet();
        Tasklist tasklist = new Tasklist(UI);
        Scanner scanner = new Scanner(System.in);
        Storage store = new Storage();
        ArrayList<Task> list = new ArrayList<>();


        store.loadFile(tasklist);
        store.cleanList();

        while(true) {
            UI.printLineBreak();
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                UI.listOut(tasklist);
            } else if (input.contains("mark ")) {
                String[] parts = input.split(" ");
                try {
                    int i = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        tasklist.mark(i);
                    } else if (parts[0].equals("unmark")) {
                        tasklist.unmark(i);
                    } else {
                        try{tasklist.addTo(input);} catch(DukeException e) {System.out.println(e.getMessage());}


                    }

                } catch (NumberFormatException e) {
                    try{
                        tasklist.addTo(input);
                    } catch(DukeException x) {
                        System.out.println(x.getMessage());}
                }
            } else if (input.startsWith("delete")) {
                try {
                    String[] strings = input.split(" ", 2);
                    int i = Integer.parseInt(strings[1]);
                    tasklist.delete(i);
                } catch (NumberFormatException x) {
                    UI.handleErrorMessage("Not a number");
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.handleErrorMessage("forget");
                }
            } else {
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
