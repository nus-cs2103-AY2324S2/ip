package saopig;

import java.util.Scanner;

/**
 * Represents the user interface of the program.
 */
public class Ui {
    private final String logo = " ____    _    ___  ____ ___ ____   ____   ___ _____ \n"
            + "/ ___|  / \\  / _ \\|  _ \\_ _/ ___| | __ ) / _ \\_   _|\n"
            + "\\___ \\ / _ \\| | | | |_) | | |  _  |  _ \\| | | || |  \n"
            + " ___) / ___ \\ |_| |  __/| | |_| | | |_) | |_| || |  \n"
            + "|____/_/   \\_\\___/|_|  |___\\____| |____/ \\___/ |_|\n";
    private final String invalidDateFormat = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have given an invalid date time format.\n "
            + "Please use the format: yyyy-MM-dd";
    private final String todoMissDetailMessage = "\n"
            + "Oh, it looks like the 'todo' command is missing some details for the task.\n "
            + "No problem at all!\n "
            + "Just add a bit more information about what you'd like to do, "
            + "and it will be as perfect as a sunny day.\n "
            + "You're doing wonderfully! ";
    private final String deadlineMissDetailMessage = "\n"
            + "Oh, it looks like the 'deadline' command is missing some details for the task.\n "
            + "No problem at all!\n "
            + "Just add a bit more information about what you'd like to do, "
            + "and it will be as perfect as a sunny day.\n "
            + "You're doing wonderfully! ";
    private final String deadlineMissTimeMessage = "\n"
            + "Whoopsie! "
            + "It seems like you may have forgotten to write the deadline time "
            + "or didn't use ' /by ' in your command.\n "
            + "Remember there is a space before and after '/by'.\n "
            + "It's a tiny detail, "
            + "but oh so important! Just add the deadline after '/by ', "
            + "and you'll be as organized as a library on a quiet morning.\n "
            + "You're doing an amazing job! ";
    private final String eventMissDetailMessage = "\n"
            + "Oh, it looks like the 'event' command is missing some details for the task.\n "
            + "No problem at all!\n "
            + "Just add a bit more information about what you'd like to do, "
            + "and it will be as perfect as a sunny day.\n "
            + "You're doing wonderfully! ";
    private final String eventMissTimeMessage = "\n"
            + "Whoopsie!\n "
            + "It seems like you may have forgotten to write the event start and end time\n "
            + "or didn't use ' /from ' or ' /to ' in your command.\n "
            + "Remember there is a space before and after '/from' and ' /to '.\n "
            + "It's a tiny detail, "
            + "but oh so important! Just add the deadline after '/by ', "
            + "and you'll be as organized as a library on a quiet morning.\n "
            + "You're doing an amazing job! ";
    private final String deleteNoArgument = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have forgotten to give an argument for the delete command.\n "
            + "Don't worry, it happens to most of us.\n "
            + "Just add the index for the task you'd like to delete, and you'll be all set.\n "
            + "Please try again, or type 'bye' to exit.";
    private final String deleteInvalidIndex = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have given an invalid index for the task list.\n "
            + "Please try again, or type 'bye' to exit.";
    private final String deleteNullTaskList = "\n"
            + "Oopses daisy!\n "
            + "It seems that taskList do not have anything inside it (return null).\n ";
    private final String listIsEmpty = "\n"
            + "Oh dear, it looks like there are no tasks yet!\n "
            + "But that's alright.\n "
            + "It gives us a chance to start fresh and dream up some new plans.\n "
            + "Whenever you're ready to add tasks, I'll be right here to assist you.\n "
            + "Let's make it a magical journey together!";
    private final String listOnDateNoArgument = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have forgotten to give an argument for the listtaskondate command.\n "
            + "Don't worry, it happens to most of us.\n "
            + "Just add the date for the task you'd like to list, and you'll be all set.\n "
            + "Please try again, or type 'bye' to exit.";
    private final String markNoArgument = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have forgotten to give an argument for the mark command.\n "
            + "Don't worry, it happens to most of us.\n "
            + "Just add the index for the task you'd like to mark, and you'll be all set.\n "
            + "Please try again, or type 'bye' to exit.";
    private final String invalidIndex = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have given an invalid index for the task list.";
    private final String unmarkNoArgument = "\n"
            + "Oopses daisy!\n "
            + "It seems like you might have forgotten to give an argument for the unmark command.\n "
            + "Don't worry, it happens to most of us.\n "
            + "Just add the index for the task you'd like to unmark, and you'll be all set.\n "
            + "Please try again, or type 'bye' to exit.";
    private final String updateMissDetail = "\n"
            + "Oh, it looks like the 'update' command is missing some details for the task.\n "
            + "No problem at all!\n "
            + "Just add a bit more information about what you'd like to do, "
            + "and it will be as perfect as a sunny day.\n "
            + "You're doing wonderfully! ";
    private Scanner scanner;
    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Prints the message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
    public String getTodoMissDetailMessage() {
        return todoMissDetailMessage;
    }
    public String getDeadlineMissDetailMessage() {
        return deadlineMissDetailMessage;
    }
    public String getDeadlineMissTimeMessage() {
        return deadlineMissTimeMessage;
    }
    public String getEventMissDetailMessage() {
        return eventMissDetailMessage;
    }
    public String getEventMissTimeMessage() {
        return eventMissTimeMessage;
    }
    public String getDeleteNoArgument() {
        return deleteNoArgument;
    }
    public String getDeleteInvalidIndex() {
        return deleteInvalidIndex;
    }
    public String getDeleteNullTaskList() {
        return deleteNullTaskList;
    }
    public String getListIsEmpty() {
        return listIsEmpty;
    }
    public String getListOnDateNoArgument() {
        return listOnDateNoArgument;
    }
    public String getInvalidDateFormat() {
        return invalidDateFormat;
    }
    public String getMarkNoArgument() {
        return markNoArgument;
    }
    public String getInvalidIndex() {
        return invalidIndex;
    }
    public String getUnmarkNoArgument() {
        return unmarkNoArgument;
    }
    public String getUpdateMissDetail() {
        return updateMissDetail;
    }
    /**
     * Prints goodbye message.
     */
    public void showGoodbye() {
        System.out.println("\n"
                + "As our time together comes to a close, "
                + "I just want to say it's been an absolute delight!\n "
                + "Remember, every day is a new adventure waiting to happen.\n "
                + "Bye for now, and take care! ");
        System.out.println(" ______   _______   _ \n"
                + "| __ ) \\ / / ____| | |\n"
                + "|  _ \\\\ V /|  _|   | |\n"
                + "| |_) || | | |___  |_|\n"
                + "|____/ |_| |_____| (_)");
    }
}
