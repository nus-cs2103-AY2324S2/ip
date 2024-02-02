package duke;

import java.time.format.DateTimeParseException;
import java.util.function.Consumer;

public class Duke {
    
    private static final Ui ui = new Ui();

    private static boolean done = false;
    
    private static final CommandList commands = new CommandList();
    
    private static TaskList tasks = new TaskList();

    private static final Storage st = new Storage("data.txt");

    public static void exit() {
        // duke.Duke will exit at the end of the loop
        done = true;
    }

    public static void addCommand(String name, Consumer<Parser> executor) {
        commands.put(name, new Command(name, executor));
    }

    public static void main(String[] mainArgs) {
        
        // initialisation
        Duke.addCommand("list", (args) -> {
            try {
                args.assertEnd();
                ui.print("Here's what you've done today...\n" + tasks.toDisplayString());
                
            } catch (DukeOptionParsingException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
        
        Duke.addCommand("bye", (args) -> {
            try {
                args.assertEnd();
                ui.print("Ok, going to sleep...");
                Duke.exit();
            } catch (DukeOptionParsingException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
        
        Duke.addCommand("mark", (args) -> {
            try {
                int index;
                Task t;

                {
                    String indexStr = args.next();
                    try {
                        index = Integer.parseInt(indexStr);
                    } catch (NumberFormatException e) {
                        throw new DukeOptionParsingException(
                                String.format("I expected a number but %s was given instead", indexStr)
                        );
                    }
                }
                
                args.assertEnd();
                
                try { 
                    t = tasks.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(
                            String.format("You tried to access an invalid task index: %d", index)
                    );
                }
                t.mark();
                ui.print("CONGRATULATION!!!!!! you completed this task:\n" + t.describe());
                Duke.st.writeTasks(tasks);
            } catch (DukeException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
        
        Duke.addCommand("unmark", (args) -> {
            try {
                int index;
                Task t;

                {
                    String indexStr = args.next();
                    try {
                        index = Integer.parseInt(indexStr);
                    } catch (NumberFormatException e) {
                        throw new DukeOptionParsingException(
                                String.format("I expected a number but %s was given instead", indexStr)
                        );
                    }
                }
                
                args.assertEnd();
                
                try { 
                    t = tasks.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(
                            String.format("You tried to access an invalid task index: %d", index)
                    );
                }
                t.unmark();
                ui.print("CONGRATULATION!!!!!! you un completed this task:\n" + t.describe());
                Duke.st.writeTasks(tasks);
            } catch (DukeException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        Duke.addCommand("todo", (args) -> {
            
            try {
                String str = args.rest();
                Task t = new ToDo(str);
                ui.print(String.format("Ok, I've added a new todo...\n  %s", t.describe()));
                tasks.add(t);
                Duke.st.writeTasks(tasks);
            } catch (DukeException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
        
        Duke.addCommand("deadline", (args) -> {
            StringBuilder by = new StringBuilder();
            StringBuilder name = new StringBuilder();
            Task t;
            
            final String NO_NAME = "you didn't specify specify a name for your deadline";
            final String NO_BY = "you failed to specify an end date using '/by'";
            
            try {
                while (!args.peek().startsWith("/")) {
                    if (!name.isEmpty()) {
                        name.append(" ");
                    }
                    name.append(args.next());
                }

                if (name.isEmpty()) {
                    throw new DukeOptionParsingException(NO_NAME);
                }

                {
                    String str = args.next();
                    if (!str.equals("/by")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", str));
                    }
                }

                while (args.hasNext()) {
                    String next = args.next();
                    if (next.startsWith("/")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", next));
                    }
                    if (!by.isEmpty()) {
                        by.append(" ");
                    }
                    by.append(next);
                }

                if (by.isEmpty()) {
                    throw new DukeOptionParsingException(NO_BY);
                }
                
                try {
                    t = new Deadline(name.toString(), by.toString());
                } catch (DateTimeParseException e) {
                    throw new DukeException("Couldn't parse the end date " + by);
                }
                
                ui.print(String.format("Ok, I've added a new deadline...\n  %s", t.describe()));
                tasks.add(t);
                Duke.st.writeTasks(tasks);
            } catch (DukeException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
            
            
        });
        
        Duke.addCommand("event", (args) -> {
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            StringBuilder name = new StringBuilder();
            Event t;


            final String NO_NAME = "you didn't specify specify a name for your event";
            final String NO_FROM = "you failed to specify an start date using '/from'";
            final String NO_TO = "you failed to specify an end date using '/to'";

            try {
                while (!args.peek().startsWith("/")) {
                    if (!name.isEmpty()) {
                        name.append(" ");
                    }
                    name.append(args.next());
                }

                if (name.isEmpty()) {
                    throw new DukeOptionParsingException(NO_NAME);
                }

                {
                    String str = args.next();
                    if (!str.equals("/from")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", str));
                    }
                }


                while (!args.peek().startsWith("/")) {
                    if (!from.isEmpty()) {
                        from.append(" ");
                    }
                    from.append(args.next());
                }

                if (from.isEmpty()) {
                    throw new DukeOptionParsingException(NO_FROM);
                }
                
                {
                    String str = args.next();
                    if (!str.equals("/to")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", str));
                    }
                }

                while (args.hasNext()) {
                    String next = args.next();
                    if (next.startsWith("/")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", next));
                    }
                    if (!to.isEmpty()) {
                        to.append(" ");
                    }
                    to.append(next);
                }

                if (to.isEmpty()) {
                    throw new DukeOptionParsingException(NO_TO);
                }
                
                try {
                    t = new Event(name.toString(), from.toString(), to.toString());
                } catch (DateTimeParseException e) {
                    throw new DukeException(String.format
                            ("Couldn't parse the start/end date %s/%s", from, to));
                }

                ui.print(String.format("Ok, I've added a new event...\n  %s", t.describe()));
                tasks.add(t);
                Duke.st.writeTasks(tasks);
            } catch (DukeException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
        
        Duke.addCommand("delete", (args) -> {
            try {
                int index;
                Task t;

                {
                    String indexStr = args.next();
                    try {
                        index = Integer.parseInt(indexStr);
                    } catch (NumberFormatException e) {
                        throw new DukeOptionParsingException(
                                String.format("I expected a number but %s was given instead", indexStr)
                        );
                    }
                }

                args.assertEnd();

                try {
                    t = tasks.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException
                            (String.format("You tried to access an invalid task index: %d", index));
                }
                tasks.remove(index);
                ui.print("I'm deleting this task. bye...\n" + t.describe());
                Duke.st.writeTasks(tasks);
            } catch (DukeException e) {
                ui.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
        
        try {
            tasks = Duke.st.loadTasks();
        } catch (DukeException e) {
            ui.print(String.format
                    ("Error loading task data: %s"
                            + "\n\nPlease delete 'data.txt' and try again. Goodbye...", e.getMessage()));
            System.exit(1);
        }
        
        ui.print("Hello, my name is... Louie!!!!\n" + 
                   "What can I do for you today?");
        while (!done) {
            String str = ui.readInput();
            Parser parser = new Parser(str);
            try {
                commands.get(parser.next()).run(parser);
            } catch (DukeCommandNotFoundException | DukeOptionParsingException e) {
                ui.print("no matching command...");
            }
        }
    }

}
