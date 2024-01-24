// Based on the parsed input, generate a response
public class Responder {
    public static void respond(String input, String[] words) {
        // default responses
        if (input.equals("bye")) {
            System.out.println(Storage.BYE);
            Parser.closeScanner();
            System.exit(0);
        }

        if (input.equals("hi")) {
            System.out.println(Storage.HI);
        }

        if (input.equals("list")) {
            Storage.printTasks();
        }

        // marking
        try {
            if (words.length == 2) {
                if (words[0].equals("mark") || words[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(words[1]) - 1;
                    Storage.markTask(words[0], taskNum);
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid integer to mark!");
            Storage.printTasks();
        }

        // adding tasks
        if (words[0].equals("todo")) {
            Parser.parseToDo(input);
            Storage.addTask(new ToDo(Storage.desc));
            Storage.report();
        }

        if (words[0].equals("deadline")) {
            Parser.parseDeadline(input);
            Storage.addTask(new Deadline(Storage.desc, Storage.by));
            Storage.report();
        }

        if (words[0].equals("event")) {
            Parser.parseEvent(input);
            Storage.addTask(new Event(Storage.desc, Storage.start, Storage.end));
            Storage.report();
        }

    }


}
