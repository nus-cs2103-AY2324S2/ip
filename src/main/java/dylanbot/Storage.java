package dylanbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

// deals with loading tasks from the file and saving tasks in the file

/**
 * Represents a Storage object that handles loading and saving of tasks to/from a file
 */
public class Storage {
    private final String taskListFilePath;
    private final String tagListFilePath;
    private final Ui ui;

    /**
     * Creates a Storage object that reads/writes to the file at the specified file path
     *
     * @param taskListFilePath The file path of the desired file
     * @param ui The Ui to be used
     */
    public Storage(String taskListFilePath, String tagListFilePath, Ui ui) {
        this.taskListFilePath = taskListFilePath;
        this.tagListFilePath = tagListFilePath;
        this.ui = ui;
    }

    /**
     * Loads and processes data from a specified file, returning it as an ArrayList of Tasks
     *
     * @return ArrayList of Tasks
     * @throws IOException If file cannot be found at the specified file path
     */
    public ArrayList<Task> loadTaskListData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(taskListFilePath));
        String nextLine;
        ArrayList<Task> res = new ArrayList<>();
        try {
            while ((nextLine = reader.readLine()) != null) {
                String[] tokens = nextLine.split(Pattern.quote(" | "));
                assert tokens.length > 0 : "Saved data is empty";
                String type = tokens[0];
                boolean isCompleted = tokens[1].equals("true");
                String desc = tokens[2];
                int numOfTags = Integer.parseInt(tokens[3]);
                ArrayList<String> tags = parseTags(numOfTags, tokens[4]);
                Task curr = createTask(type, desc, isCompleted, numOfTags, tags, tokens);
                res.add(curr);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     * Parses the tags from the input String and returns them as an ArrayList
     *
     * @param numOfTags The number of tags to be parsed
     * @param tokens The input String to be parsed
     * @return ArrayList of tags
     */
    public ArrayList<String> parseTags(int numOfTags, String tokens) {
        ArrayList<String> tagList = new ArrayList<>();
        String[] tags = tokens.split(" ");
        int currIdx = 4;
        if (numOfTags > 0) {
            for (int i = 0; i < numOfTags; i++) {
                tagList.add(tags[i].substring(1));
                currIdx++;
            }
        }
        return tagList;
    }

    /**
     * Creates a Task object based on the input parameters
     *
     * @param type The type of Task to be created
     * @param desc The description of the Task
     * @param isCompleted Whether the Task is completed
     * @param numOfTags The number of tags the Task has
     * @param tags The tags of the Task
     * @param tokens The input String to be parsed
     * @return The created Task
     */
    public Task createTask(String type, String desc, boolean isCompleted, int numOfTags,
                           ArrayList<String> tags, String[] tokens) {
        Task curr;
        switch (type) {
        case "T":
            curr = new TodoTask(desc, tags);
            break;
        case "D":
            curr = new DeadlineTask(desc, tags, convertStringToDateTime(tokens[4 + numOfTags]));
            break;
        case "E":
            curr = new EventTask(desc, tags, convertStringToDateTime(tokens[4 + numOfTags]),
                    convertStringToDateTime(tokens[5 + numOfTags]));
            break;
        default:
            curr = new TodoTask(desc, tags);
            break;
        }
        if (isCompleted) {
            curr.mark();
        }
        return curr;
    }

    /**
     * Writes data to a specified file based on the provided ArrayList of Tasks
     *
     * @param tl The data to be saved
     * @throws IOException If file path provided is invalid for a file to be created at
     */
    public void saveTaskListData(TaskList tl) throws IOException {
        ArrayList<Task> tasks = tl.getTasks();
        File newFile = new File(taskListFilePath);
        boolean isDirectoryCreated = newFile.getParentFile().mkdirs();
        assert !isDirectoryCreated : "Directory should be created if needed";
        newFile.createNewFile();

        FileWriter writer = new FileWriter(taskListFilePath);
        for (Task t : tasks) {
            StringBuilder data = new StringBuilder();
            data.append(t.getType()).append(" | ").append(t.checkCompleted()).append(" | ").append(t.getDesc());

            data.append(" | ").append(t.getNumOfTags());
            if (t.getNumOfTags() > 0) {
                data.append(" | ").append(t.getTags());
            }

            if (t.getType().equals("D")) {
                data.append(" | ").append(((DeadlineTask) t).getDeadline());
            } else if (t.getType().equals("E")) {
                data.append(" | ").append(((EventTask) t).getFrom()).append(" | ").append(((EventTask) t).getTo());
            }

            writer.write(data.toString());
            writer.write("\n");
        }
        writer.close();
    }

    /**
     * Loads and processes data from a specified file, returning it as a HashMap of tags and their corresponding indices
     *
     * @return HashMap of tags and their corresponding indices
     * @throws IOException If file cannot be found at the specified file path
     */
    public HashMap<String, ArrayList<Integer>> loadTagListData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(tagListFilePath));
        String nextLine;
        HashMap<String, ArrayList<Integer>> res = new HashMap<>();
        try {
            while ((nextLine = reader.readLine()) != null) {
                String[] tokens = nextLine.split(Pattern.quote(" | "));
                assert tokens.length > 0 : "Saved data is empty";
                String tag = tokens[0];
                String[] idxStr = tokens[1].split(" ");
                ArrayList<Integer> indices = new ArrayList<>();
                for (String s : idxStr) {
                    int idx = Integer.parseInt(s);
                    indices.add(idx);
                }
                res.put(tag, indices);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;

    }

    /**
     * Writes data to a specified file based on the provided HashMap of tags and their corresponding indices
     *
     * @param tl The data to be saved
     * @throws IOException If file path provided is invalid for a file to be created at
     */
    public void saveTagListData(TaskList tl) throws IOException {
        HashMap<String, ArrayList<Integer>> tagList = tl.getTagList();
        File newFile = new File(tagListFilePath);
        boolean isDirectoryCreated = newFile.getParentFile().mkdirs();
        assert !isDirectoryCreated : "Directory should be created if needed";
        newFile.createNewFile();

        FileWriter writer = new FileWriter(tagListFilePath);
        for (String tag : tagList.keySet()) {
            StringBuilder data = new StringBuilder();
            data.append(tag).append(" | ");
            ArrayList<Integer> indices = tagList.get(tag);
            for (int idx : indices) {
                data.append(idx).append(" ");
            }
            writer.write(data.toString());
            writer.write("\n");
        }

        writer.close();
    }


    /**
     * Converts an input String to LocalDateTime and returns it
     *
     * @param input String to be converted
     * @return The converted String as a LocalDateTime
     */
    public static LocalDateTime convertStringToDateTime(String input) {
        LocalDateTime deadline;
        if (input.length() < 11) {
            deadline = LocalDate.parse(input).atStartOfDay();
        } else {
            deadline = LocalDateTime.parse(input);
        }
        return deadline;
    }
}

