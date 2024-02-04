package BadApple.task;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class Tracker {
    public static boolean suppressMessages = false;
    /**
     * When a user adds a task, a response of PrependMessages + task is rendered
     * To add a message: Write the enum and then place a prepend and append message in brackets
     * e.g. ANGRY("You better get your ass up and", "otherwise Freddy will get you"),
     */
    enum CustomMessages {

        DEFAULT("Added: ", ""),
        SUSPENSE("Are you sure you want to: ", " Mewo looks at you, frightened"),
        HAPPY("I'm sure you can ", " I'll always be By Your Side"),
        DEMEANING("Don't tell me you can't ", " don't disappoint me");
        private final String prepend;
        private final String append;
        private static final Random RNG = new Random();
        CustomMessages(String prepend, String append) {
            this.prepend = prepend;
            this.append = append;
        }

        private static String getPrepend(CustomMessages msg) {
            return msg.prepend;
        }
        private static String getAppend(CustomMessages msg) {
            return msg.append;
        }

        public static String randomMsg(Task task) {
            CustomMessages[] pm = values();
            int msgIndex = RNG.nextInt(pm.length);
            return getPrepend(pm[msgIndex]) + task.brief() + getAppend(pm[msgIndex]);
        }
    }





}
