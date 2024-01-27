package duke;

import duke.exceptions.HistoryIndexException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.util.Scanner;

public class UI {

  Storage manager;
  Parser parser;
  TaskList history;
  Scanner sc;

  enum Command {
    BYE,
    LIST,
    EVENT,
    TODO,
    DEADLINE,
    MARK,
    UNMARK,
    DELETE
  }

  public UI(Storage manager, Parser parser, TaskList history) {
    this.history = history;
    this.manager = manager;
    this.parser = parser;
    this.sc = new Scanner(System.in);
    sayHi();
  }

  public void sayHi() {
    String name = "shu heng";
    String name_display = "_________________________\n" +
      "Hello! I'm " + name + "\n" +
      "What can I do for you?\n" +
      "_________________________\n";
    System.out.println(name_display);
  }

  public void sayBye() {
    String final_print = "_________________________\n" +
      "Bye. Hope to see you again soon!\n" +
      "_________________________\n";
    System.out.println(final_print);
    this.sc.close();
  }

  public void run() {
    mainloop: while (true) {
      String current_input = this.sc.nextLine();
      String[] current_input_split = current_input.split(" ");
      Command curr_command = null;
      try {
        curr_command = parser.getCommand(current_input_split);
      } catch (InvalidTaskException e) {
        System.out.println("That's not a valid input :(");
        System.out.println(e.getMessage());
        continue;
      }
      switch (curr_command) {
        case LIST:
          history.List();
          break;
        case  BYE:
          this.sayBye();
          return;
        case DELETE:
          int focus_index = -1;
          try {
            focus_index = parser.checkIndexGiven(current_input_split[1],
              history.getLength());
          } catch (HistoryIndexException e) {
            System.out.println("Invalid index selected!");
            continue;
          }
          history.deleteTask(focus_index);
          break;
        case MARK:
          focus_index = -1;
          try {
            focus_index = parser.checkIndexGiven(current_input_split[1],
              history.getLength());
          } catch (HistoryIndexException e) {
            System.out.println("Invalid index selected!");
            continue;
          }
          history.markTask(focus_index);
          break;
        case UNMARK:
          focus_index = -1;
          try {
            focus_index = parser.checkIndexGiven(current_input_split[1],
              history.getLength());
          } catch (HistoryIndexException e) {
            System.out.println("Invalid index selected!");
            continue;
          }
          history.unmarkTask(focus_index);
          break;
        case EVENT:
          Task event = null;
          String[] data;
          try {
            data = parser.extractDescriptionData(current_input_split);
          } catch (InvalidInputException e) {
            System.out.println("That's not a valid input :(");
            System.out.println(e.getMessage());
            continue mainloop;
          }
          try {
            event = new Events(data[0], parser.parseDate(data[1]), parser.parseDate(data[2]));
            history.addTask(event);
          } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
          }
          break;
        case TODO:
          event = null;
          try {
            data = parser.extractDescriptionData(current_input_split);
          } catch (InvalidInputException e) {
            System.out.println("That's not a valid input :(");
            System.out.println(e.getMessage());
            continue mainloop;
          }
          event = new ToDos(data[0]);
          history.addTask(event);
          break;
        case DEADLINE:
          try {
            data = parser.extractDescriptionData(current_input_split);
          } catch (InvalidInputException e) {
            System.out.println("That's not a valid input :(");
            System.out.println(e.getMessage());
            continue;
          }
          try {
            event = new Deadlines(data[0], parser.parseDate(data[1]));
            history.addTask(event);
          } catch (InvalidInputException e) {
            System.out.println("Invalid Input: " + e.getMessage());
          }
          break;
      }
    }
  }
}
