package fishstock;

import java.util.Arrays;

import fishstock.Command.Keyword;

class TaskFactory {
    protected static Task of(UserInput input) throws FishStockException {
        Keyword keyword = input.getCommandType();

        assert Arrays.asList(Keyword.TODO, Keyword.DEADLINE, Keyword.EVENT)
                .contains(keyword) : "Attempted to add an invalid Task";

        Task task = null;
        switch (keyword) {
        case TODO:
            task = Todo.of(input);
            break;
        case DEADLINE:
            task = Deadline.of(input);
            break;
        case EVENT:
            task = Event.of(input);
            break;
        default:
            // Not possible as checked beforehand.
        }
        return task;
    }

    protected static Task fromStorageString(String line) throws FishStockException {
        String[] arr = line.split("\\|");
        Keyword keyword = Keyword.findShortened(arr[0]);
        boolean isDone = getIsDone(arr[arr.length - 1]);

        Task task;
        switch (keyword) {
        case TODO:
            task = new Todo(arr[1], isDone);
            break;
        case DEADLINE:
            task = new Deadline(arr[1], isDone, Parser.parseDate(arr[2]));
            break;
        case EVENT:
            task = new Event(arr[1], isDone, Parser.parseDate(arr[2]), Parser.parseDate(arr[3]));
            break;
        default:
            throw new FishStockException("Wrong format..");
        }

        return task;
    }

    private static boolean getIsDone(String markStatus) throws FishStockException {
        if ("1".equals(markStatus)) {
            return true;
        } else if ("0".equals(markStatus)) {
            return false;
        } else {
            throw new FishStockException("Mark status corrupted..");
        }
    }
}
