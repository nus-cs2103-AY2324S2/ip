public class Parser {

    public static Command parseCommand(String input) {
        if(input.startsWith("unmark")){
            return Command.Unmark;
        }
        else if(input.startsWith("mark")){
            return Command.Mark;
        }
        else if(input.startsWith("delete")){
            return Command.Delete;
        }
        else if (input.startsWith("todo")){
            return Command.Todo;
        }
        else if (input.startsWith("deadline")){
            return Command.Deadline;
        }
        else if (input.startsWith("event")){
            return Command.Event;
        }
        else if (input.startsWith("list")){
            return Command.List;
        }
        else if(input.startsWith("bye")){
            return Command.Bye;
        } else{
            return Command.Invalid;
        }
    }

    public static String parseDetail(String input){
        int detailIndex = input.indexOf(" ");
        if (detailIndex == -1){
            return "";
        } else {
            return input.substring(detailIndex + 1);
        }
    }
}
