// Based on the parsed input, generate a response
public class Responder {
    public static void respond(String[] words) {
        switch(words[0]) {
        case "hi":
            System.out.println(Storage.HI);
            break;
        case "list":
            Storage.printTasks();
            break;
        case "mark":
            Storage.markTask(MarkType.MARK);
            break;
        case "unmark":
            Storage.markTask(MarkType.UNMARK);
            break;
        case "delete":
            Storage.deleteTask();
            break;
        case "todo":
        case "deadline":
        case "event":
            Storage.addTask();
            break;
        default:
            System.out.println("  I don't understand! Try adding a task!");
        }
    }
}
