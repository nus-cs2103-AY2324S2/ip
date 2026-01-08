package ui;

import tasks.Deadline;
import tasks.Event;

public class Ui {

    public String showInstructions() {
        return "Please type in:\n"
                + "------------------------\n"
                + "[for command information]\n"
                + "- help\n\n"
                + "[to record your task(s)/ events]\n"
                + "- todo <task>\n"
                + "- deadline <task> /by " + Deadline.DATE_FORMAT + "\n"
                + "- event <event> /from " + Event.DATE_FORMAT + " /to " + Event.DATE_FORMAT + "\n\n"
                + "[to view your task(s)]\n"
                + "- list\n\n"
                + "[to search for tasks containing a keyword]\n"
                + "- find <keyword>\n\n"
                + "[to mark or unmark your task as done]\n"
                + "- mark <task number in list>\n"
                + "- unmark <task number in list>\n\n"
                + "[to delete a task]\n"
                + "- delete <task number in list>\n\n"
                + "[to update a task]\n"
                + "- update <task number in list> /[task] <task> [details]\n"
                + "   e.g.\n   update 1 /deadline/ sleep /by/ 2024-02-03 23:00\n"
                + "   update 1 /todo/ sleep\n"
                + "   update 3 /event/ sleep /from/ 3pm /to/ 11pm\n"
                + "-------------------------------------\n";
    }

    public String greet() {
        return "    Hi! I am your favourite friend, Lelu :)\n    What can I do for you?\n\n"
                + "    For more information, enter:\n    help\n";
    }

    public String exit() {
        return "    Ok bye, you shall be missed :(";
    }


    public String dateFormatInstructions() {
        return String.format("    Your date should be in this format:\n    "
                + "<" + Deadline.DATE_FORMAT + ">" + " e.g. 2024-02-03 15:25\n\n");
    }


}
