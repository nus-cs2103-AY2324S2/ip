package naruto;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import action.Action;
import action.Greet;
import action.HandleError;
import exception.NarutoException;
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
