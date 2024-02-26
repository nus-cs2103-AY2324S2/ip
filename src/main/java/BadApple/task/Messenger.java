package BadApple.task;

import java.util.Random;

/**
 * This class is to add personality to the chatbot
 * Do add extra enums to customise the replies Mari can give
 */
public class Messenger {
    public static boolean isSuppressingMessages = false;
    /**
     * When a user adds a task, a response of PrependMessages + task is rendered
     * To add a message: Write the enum and then place a prepend and append message in brackets
     * e.g. ANGRY("You better get your ass up and", "otherwise Freddy will get you"),
     */
    enum CustomMessages {

        DEFAULT("Added: ", ""),
        SUSPENSE("Are you sure you want to: ", " Mewo looks at you, frightened"),
        HAPPY("I'm sure you can ", " I'll always be By Your Side"),
        DEMEANING("Don't tell me you can't ", " don't disappoint me"),
        UNKNOWN_CMD("I didn't quite catch that", "if you need help on what to say, type !help");
        private final String prepend;
        private final String append;
        private static final Random RNG = new Random();
        CustomMessages(String prepend, String append) {
            this.prepend = prepend;
            this.append = append;
        }

        public static String getPrepend(CustomMessages msg) {
            return msg.prepend;
        }
        public static String getAppend(CustomMessages msg) {
            return msg.append;
        }

        /**
         * Used to give Mari a personality by attaching a random msg to the task added
         * @param task the Task to be added
         * @return a random message to go with the Task description
         */
        public static String randomMsg(Task task) {
            CustomMessages[] pm = values();
            int msgIndex = RNG.nextInt(pm.length - 1);
            return getPrepend(pm[msgIndex]) + task.brief() + getAppend(pm[msgIndex]);
        }
    }





}
