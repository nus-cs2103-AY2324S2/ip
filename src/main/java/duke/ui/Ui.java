package duke.ui;

import duke.task.Task;
import java.util.List;
import java.util.Scanner;

public class Ui {

	/** Line separator */
	private static final String TXT_LINE = "\t____________________________________________________________\n";
	/** Text printed if description of task is not given */
	private static final String TXT_NODESC = "\tPlease provide the description of your task!\n";

	/**
	 * Prints welcome lines for user during initial start-up
	 */
	public void intro() {
		String introTxt = TXT_LINE +
				"\t Hello! I'm Megatron\n" +
				"\t What can I do for you?\n" +
				TXT_LINE;
		System.out.println(introTxt);
	}

	/**
	 * Reads keyboard commands by the user
	 * @return String of instructions given
	 */
	public String readCommand() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/**
	 * Prints exit lines to the user
	 */
	public void exit() {
		String outroTxt = TXT_LINE + "\t Bye. Hope to see you again soon!\n" + TXT_LINE;
		System.out.println(outroTxt);
	}

	/**
	 * Prints the list of tasks given
	 *
	 * @param store List<Task> of tasks to be printed
	 */
	public void list(List<Task> store) {
		StringBuilder listingTxt = new StringBuilder("\tHere are the tasks in your list:\n");
		for (int i = 0; i < store.size(); i++) {
			listingTxt.append("\t ").append(i + 1).append(".").append(store.get(i)).append("\n");
		}
		System.out.println(TXT_LINE + listingTxt + TXT_LINE);
	}

	/**
	 * Prints details of task added to the user
	 *
	 * @param newTask task that was added
	 * @param numItems number of items currently in the list
	 */
	public void addTask(Task newTask, int numItems) {
		String addTaskTxt = "\tGot it. I've added this task:\n";
		System.out.println(TXT_LINE + addTaskTxt + "\t\t" + newTask + "\n"
				+ "\tNow you have " + numItems + " tasks in the list.\n"
				+ TXT_LINE
		);
	}

	/**
	 * Prints details of task deleted to the user
	 *
	 * @param toRemove task that was deleted
	 * @param numItems number of items left in the list
	 */
	public void deleteTask(Task toRemove, int numItems) {
		String deleteTxt = "\tNoted. I will remove this task for you:\n";
		System.out.println(TXT_LINE + deleteTxt + "\t\t" + toRemove + "\n" +
				"\tNow you have " + numItems + " tasks in the list.\n" +
				TXT_LINE
		);
	}

	/**
	 * Prints details of task updated to the user
	 *
	 * @param updateTask task that was updated
	 * @param isComplete completion status of updated task
	 */
	public void mark(Task updateTask, boolean isComplete) {
		String markTxt = "\tNice! I've marked this task as done:\n";
		String unmarkTxt = "\tOK, I've marked this task as not done yet:\n";
		String markFormTxt = "\tSorry! To mark or unmark tasks, please do\n" +
				"\t\t(un)mark (number)\n";

		String toPrint = isComplete ? markTxt : unmarkTxt;
		System.out.println(TXT_LINE + toPrint +
				"\t\t" + updateTask + "\n" + TXT_LINE);
	}

	/**
	 * Prints error line to the user
	 *
	 * @param errorMsg containing details of the issue
	 */
	public void showError(String errorMsg) {
		if (errorMsg.equals("Description Blank")) {
			System.out.println(TXT_LINE + TXT_NODESC + TXT_LINE);
		} else if (errorMsg.equals("Invalid Number")) {
			System.out.println(TXT_LINE + "\tInvalid number given! :(\n"
			+ TXT_LINE);
		} else {
			System.out.println(TXT_LINE + errorMsg + TXT_LINE);
		}
	}


}
