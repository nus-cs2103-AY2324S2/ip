package duke;

import exceptions.DukeEmptyArgumentException;
import exceptions.DukeErroneousArgumentException;
import exceptions.DukeInvalidInputException;
import exceptions.DukeWrongDateOrderException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * This class helps the chatbot to parse inputs in order to execute the reactions that the user wants.
 *
 * @author Lim Zi Jia
 */
public class Parser {
    /**
     * The main parsing logic. Takes the input and calls the relevant functions for the desired outputs.
     *
     * @param in The string input that the user has passed into the program.
     * @param tasks A list of the current tasks that maybe needed to modify.
     * @param storage The place where the task list might be needed to be stored.
     * @return A TaskList that has been altered if there is a need to. The value is null if the command is 'bye'
     */
    public TaskList parse(String in, TaskList tasks, Storage storage) {
        if (in.equals("bye")) {
            return null;
        }

        else if (in.equals("list")) {
            tasks.printList();
        }

        else if (in.equals("save")) {
            try {
                storage.save(tasks);
            } catch (IOException e) {
                System.out.println("I/O Exception");
            }
        }

        else if (in.startsWith("mark ")) {
            tasks.mark(in);
        }

        else if (in.startsWith("unmark ")) {
            tasks.unmark(in);
        }

        else if (in.startsWith("delete ")) {
            tasks.delete(in);
        }

        else {
            try {
                tasks = addTask(in, tasks);
            } catch (DukeInvalidInputException e) {
                System.out.println("This is not a valid input!!!");
            } catch (DukeEmptyArgumentException e) {
                System.out.println("There is an argument that is empty!!!");
            } catch (DukeErroneousArgumentException e) {
                System.out.println("There is an argument in the wrong format!!!");
            } catch (DateTimeException e) {
                System.out.println("The format of your date is wrong! Make sure it is of the form 'yyyy-MM-dd'.");
                System.out.println("More specifically: \n" + e.getMessage());
            } catch (DukeWrongDateOrderException e) {
                System.out.println("The end date should be after the start date");
            }
        }
        return tasks;
    }

    /**
     * A helper function that is dedicated to adding tasks.
     *
     * @param s String of input to be parsed.
     * @param tasks The original task list to be added onto.
     * @return The edited task list.
     */
    public TaskList addTask(String s, TaskList tasks) {
        // Todo_
        if (s.startsWith("todo ")) {
            // Get name and if it is empty, throw exception
            String n = s.substring(5);
            if (n.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }

            tasks.add(new Todo(n));

            // tasks.Deadline
        } else if (s.startsWith("deadline ")) {
            // Try to get the index of the first '/', if it does not exist, the statement is invalid.
            // Also, it should adhere to "/by"
            int first = s.indexOf('/');
            if (first == -1) {
                throw new DukeErroneousArgumentException();
            } else if (!s.startsWith("/by ", first)) {
                throw new DukeErroneousArgumentException();
            }

            // Get name and time. If empty, throw exception
            String n = s.substring(9, Math.max(first - 1, 9));
            String t = s.substring(first + 4);
            if (n.isEmpty() || t.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }

            tasks.add(new Deadline(n, t));

            // tasks.Event
        } else if (s.startsWith("event ")) {
            // Try to get the index of the first  and second '/', if it does not exist, the statement is invalid.
            // Also, the format should adhere to "/from" and "/to"
            int first = s.indexOf('/');
            int second = s.indexOf('/', first + 1);
            if (first == -1) {
                throw new DukeErroneousArgumentException();
            } else if (!s.startsWith("/from ", first)
                    || !s.startsWith("/to ", second)) {
                throw new DukeErroneousArgumentException();
            }

            String n = s.substring(6, Math.max(first - 1, 6));
            String f = s.substring(first + 6,  Math.max(second - 1, first + 6));
            String t = s.substring(second + 4);
            if (n.isEmpty() || f.isEmpty() || t.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }
            Event e = new Event(n, f, t);
            if (!e.isCorrectOrder()) {
                throw new DukeWrongDateOrderException();
            }
            tasks.add(e);

        } else {
            throw new DukeInvalidInputException();
        }

        System.out.print("Got it added this task:\n  " + tasks.get(tasks.size() - 1));
        System.out.printf("Now you have %d tasks in the list.", tasks.size());
        return tasks;
    }
}
