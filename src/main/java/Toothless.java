import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Toothless {
    private String splitLine = "____________________________________________________________";
    private String chatBotName = "Toothless";
    private String greetingString = "Hi! "+ chatBotName +" is " + chatBotName + "!\n"
                            + "What can " + chatBotName + " do for human?\n" + splitLine;

    public Toothless(String filepath){
        try {
            Command.loadTasks(filepath);
        } catch (FileNotFoundException e){
            System.out.println("Can't Find Task File!");
        } catch (ToothlessException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Scanner sc) {
        String input;
        Command command;
        boolean isDone = false;
        System.out.println(splitLine + "\n" + greetingString);
        while(!isDone){
            input = sc.nextLine();
            System.out.println(splitLine);

            int detailIndex = input.indexOf(" ");
            String detail;
            if (detailIndex == -1){
                detail = "";
            } else {
                detail = input.substring(detailIndex + 1);
            }

            if(input.startsWith("unmark")){
                command = Command.Unmark;
            }
            else if(input.startsWith("mark")){
                command = Command.Mark;
            }
            else if(input.startsWith("delete")){
                command = Command.Delete;
            }
            else if (input.startsWith("todo")){
                command = Command.Todo;
            }
            else if (input.startsWith("deadline")){
                command = Command.Deadline;
            }
            else if (input.startsWith("event")){
                command = Command.Event;
            }
            else if (input.startsWith("list")){
                command = Command.List;
            }
            else if(input.startsWith("bye")){
                command = Command.Bye;
            } else{
                command = Command.Invalid;
            }

            isDone = Command.handleCommand(command, detail);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Toothless toothless = new Toothless("./data/toothless.txt");
        toothless.start(sc);
    }
}
