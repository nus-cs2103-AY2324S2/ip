import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Codec {

    String delimiter = ", ";

    public String encode(Task task) {
        String[] encodedTask = task.encode();
        String encodedTaskString = buildString(encodedTask);
        return encodedTaskString;
    }


    public String buildString(String... values) {
        StringJoiner encodedString = new StringJoiner(delimiter);
        for (String string : values) {
            encodedString.add(string);
        }

        return encodedString.toString();
    }

    public Task decode(String string) throws ProcessingException{
        String[] splitString = string.split(delimiter);
        switch (splitString[0]) {
            case "D":
                return decodeDeadline(splitString);
            case "T":
                return decodeTodo(splitString);
            case "E":
                return decodeEvent(splitString);

            default:
                throw new ProcessingException("Error while decoding identifier");
        }
    }
    
    
    public Task decodeDeadline(String[] splitString) throws ProcessingException{
        try {
            Boolean done = Boolean.parseBoolean(splitString[1]);

            String taskName = splitString[2];
            LocalDateTime by = TimeProcessor.fromString(splitString[3]);

            return new Deadline(taskName, by, done);
        } catch (Exception e) {
            throw new ProcessingException("Error while decoding Deadline", e);

        }
    }

    public Task decodeEvent(String[] splitString) throws ProcessingException{
        try {
            Boolean done = Boolean.parseBoolean(splitString[1]);

            String taskName = splitString[2];
            LocalDateTime from = TimeProcessor.fromString(splitString[3]);
            LocalDateTime to = TimeProcessor.fromString(splitString[4]);

            return new Event(taskName, from, to, done);

        } catch (Exception e) {
            throw new ProcessingException("Error while decoding Event", e);
        }
    }

    public Task decodeTodo(String[] splitString) throws ProcessingException{
        try {
            Boolean done = Boolean.parseBoolean(splitString[1]);

            String taskName = splitString[2];

            return new Todo(taskName, done);

        } catch (Exception e) {
            throw new ProcessingException("Error while decoding Todo", e);

        }
    }

} 