package duke.history;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.exceptions.ProcessingException;
import duke.exceptions.StartUpException;
import duke.storage.Storage;



/**
 * placeholder
 */
public class HistoryManager {
    private History history;
    private final String historyFilePath;
    private final File historyFile;

    /**
     * @param fileDirectory placeholder
     * @param fileName      placeholder
     */
    public HistoryManager(String fileDirectory, String fileName, State startState) throws StartUpException {
        historyFilePath = fileDirectory + fileName;
        new File(fileDirectory).mkdir();
        historyFile = new File(historyFilePath);
        startUpHistory(startState);
    }

    private void startUpHistory(State startState) throws StartUpException {
        assert history == null;
        try {
            boolean hasCreatedNewFile = historyFile.createNewFile();
            if (hasCreatedNewFile) {
                System.out.println("History file not found! Created an new one!");
                history = new History(startState);
                saveHistory();
            } else {
                System.out.println("History file found! Loading old history...");
                history = loadHistory();
                history.addState(startState);
            }
        } catch (ProcessingException e) {
            throw new StartUpException(e.getMessage(), e);
        } catch (IOException e) {
            String message = "An error occurred with the history file at the system level. Try again";
            throw new StartUpException(message, e);
        }
    }

    private History loadHistory() throws ProcessingException {
        try {
            FileInputStream fileInputStream = new FileInputStream(historyFilePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            History newHistory = (History) objectInputStream.readObject();
            objectInputStream.close();
            return newHistory;
        } catch (IOException e) {
            String message = "An error occurred with the history file at the system level. Try again";
            throw new ProcessingException(message, e);

        } catch (ClassNotFoundException e) {
            String message = "An error occurred with parsing the history file. Try again";
            throw new ProcessingException(message, e);
        }
    }
    private void saveHistory() throws ProcessingException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(historyFilePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(history);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            String message = "An error occurred with the history file at the system level. Try again";
            throw new ProcessingException(message, e);
        }
    }
    /**
     * @return placeholder
     * @throws ProcessingException placeholder
     */
    public String undo(Storage storage) throws ProcessingException {
        State prevState = history.getCurrState();
        history.rollBackState();
        //saveHistory();
        State currState = history.getCurrState();
        System.out.println(history.toString());
        storage.restoreState(currState);
        return String.format("Your %s command was undone!\nThis is your current list\n%s",
                prevState.getCommand(),
                storage.displayList());
    }

    /**
     * @return placeholder
     * @throws ProcessingException placeholder
     */
    public String redo(Storage storage) throws ProcessingException {
        history.rollForwardState();
        //saveHistory();
        State currState = history.getCurrState();
        storage.restoreState(currState);
        return String.format("Your %s command was redone!\nThis is your current list\n%s",
                currState.getCommand(),
                storage.displayList());
    }
    /**
     * @param state placeholder
     * @throws ProcessingException placeholder
     */
    public void updateHistory(State state) throws ProcessingException {
        if (state.isIgnoredHistory()) {
            return;
        }
        history.addState(state);
        System.out.println(history.getCurrStateIdx());
        //saveHistory();
    }
}
