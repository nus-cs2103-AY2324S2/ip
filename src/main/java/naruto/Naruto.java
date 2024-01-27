package naruto;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import action.Action;
import action.Goodbye;
import action.Add;
import action.Delete;
import action.Greet;
import action.HandleError;
import action.List;
import action.Mark;
import action.Unmark;
import exception.NarutoException;
import task.Deadline;
import task.Event;
import task.ToDo;
import util.*;

public class Naruto {
    private static LinkedList<Action> actions = new LinkedList<>();
    private static TaskList taskList;

    static {
        try {
            taskList = new TaskList();
        } catch (IOException e) {
            PrintUtil.print(NarutoException.createFileCorruptedException().getMessage());
        }
    }

    private static Scanner sc = new Scanner(System.in);

    public Naruto() {
        actions.add(new Greet());
    }

    public static void listen() {
        actions.add(Ui.parseInput(taskList));
    }
    public static void act() {
        try {
            actions.pollFirst().execute();
        } catch (IOException e) {
            handleException(NarutoException.createInvalidActionException());
        }
    }

    public static boolean hasNextAction() {
        return !actions.isEmpty();
    }

    public static void handleException(NarutoException err) {
        actions.addLast(new HandleError(err));
    }

}
