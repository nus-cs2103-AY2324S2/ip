// Based on the parsed input, generate a response
public class Responder {
    public static void respond(String[] words) {
        switch(words[0]) {
            case "bye":
                System.out.println(Storage.BYE);
                Parser.closeScanner();
                System.exit(0);
                break;
            case "hi":
                System.out.println(Storage.HI);
                break;
            case "list":
                Storage.printTasks();
                break;
            case "mark":
            case "unmark":
                Parser.parseMarkTask(words);
                break;
            case "todo":
            case "deadline":
            case "event":
                Storage.addTask(words[0]);
                break;
            default:
                System.out.println("  I don't understand! Try adding a task!");
        }
    }
}
