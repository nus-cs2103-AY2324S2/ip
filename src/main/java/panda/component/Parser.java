package panda.component;

import panda.command.*;
import panda.exception.*;
import panda.task.*;

public class Parser {
    /**
     * Parses the user input into a Command.
     * 
     * @param userInput the user input to parse.
     * @return the parsed Command.
     * @throws PandaException if the user input does not correspond to a known command.
     */
    public static Command parse(String userInput) throws PandaException {
        String commandWord = userInput.split(" ")[0].trim();
        try {
            if(commandWord.equals("bye")) {
                return new ExitCommand();
            }
            if(commandWord.equals(("list"))) {
                return new PrintListCommand();
            }
            if(commandWord.equals("help")) {
                return new CommandListCommand();
            }
            if(commandWord.equals("mark")) {
                int target;
                target = Integer.parseInt(userInput.split(" ", 2)[1]);
                return new AlterMarkCommand(target, true);
            }
            if(commandWord.equals("unmark")) {
                int target;
                target = Integer.parseInt(userInput.split(" ", 2)[1]);
                return new AlterMarkCommand(target, false);
            }
            if(commandWord.equals("delete")) {
                int target;
                target = Integer.parseInt(userInput.split(" ", 2)[1]);
                return new DeleteCommand(target);
            }
            if(commandWord.equals("find")) {
                String argString = userInput.trim().split(" ", 2)[1].trim();
                return new FindCommand(argString);
            }
            if(commandWord.equals("filter")) {
                String argString = userInput.trim().split(" ", 2)[1].trim();
                return new FilterCommand(argString);
            }
            if(commandWord.equals("tag")) {
                String argString = userInput.trim().split(" ", 2)[1].trim();
                int target;
                target = Integer.parseInt(argString.split(" ", 2)[0]);
                return new ModifyTagCommand(target, argString.split(" ", 2)[1], true);
            }
            if(commandWord.equals("untag")) {
                String argString = userInput.trim().split(" ", 2)[1].trim();
                int target;
                target = Integer.parseInt(argString.split(" ", 2)[0]);
                return new ModifyTagCommand(target, argString.split(" ", 2)[1], false);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException();
        }
        if(commandWord.equals("todo")) {
            String[] parts = userInput.trim().split(" ", 2);
            if(parts.length < 2) {
                throw new EmptyTodoException();
            }
            return new NewTaskCommand(new Todo(parts[1].trim()));
        }
        if(commandWord.equals("deadline")) {
            String[] parts = userInput.trim().split(" ", 2);
            if(parts.length < 2) {
                throw new EmptyDeadlineException("desc");
            }
            String[] args = parts[1].split("/by");
            if(args.length < 2) {
                throw new EmptyDeadlineException("date");
            }
            return new NewTaskCommand(new Deadline(args[0].trim(), args[1].trim()));
        }
        if(commandWord.equals("event")) {
            String[] parts = userInput.trim().split(" ", 2);
            if(parts.length < 2) {
                throw new EmptyEventException("desc");
            }
            String[] args = parts[1].split("/from");
            if(args.length < 2 || args[1].split("/to").length < 2) {
                throw new EmptyEventException("date");
            }
            return new NewTaskCommand(new Event(
                args[0].trim(), 
                args[1].split("/to")[0].trim(), 
                args[1].split("/to")[1].trim()));
        }
        throw new UnknownCommandException();
    } 
}
