package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

public class UI {
	private Scanner scanner = new Scanner(System.in);

	public void onEnter() {
		System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
	}

	public void showList(TaskList taskList) {
		System.out.println("Here are the tasks in your list:");
		taskList.listTask();
	}

	public void onAddSuccess(Task task, Integer count) {
		System.out.println("Got it. I've added this task:");
		System.out.println(task);
		this.showTaskListCount(count);
	}

	public void onMarkDone(Task task) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(task);
	}

	public void onUnmarkDone(Task task) {
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(task);
	}

	public void onDelete(Task task, TaskList taskList) {
		System.out.println("Noted. I've removed this task:");
		System.out.println(task);
		this.showTaskListCount(taskList.getNumOfTasks());
	}

	public void showErrorMsg(String msg) {
		System.out.println(msg);
	}

	public void showLoadingError() {
		System.out.println("Error occur when initiating the resources.");
	}

	public String getUserInput() {
		return this.scanner.nextLine();
	}

	public void onExit() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	public void showTaskListCount(Integer count) {
		System.out.println("Now you have " + count + " tasks in the list.");
	}
}
