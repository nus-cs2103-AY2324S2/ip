public class Constants {
    public static final String introPrint = "----------------------------------------------------------------------------------\n"
            + "    BMO chop!\n    Do you want to play video games?\n"
            + "----------------------------------------------------------------------------------\n";
    public static final String tutorialPrint = "Command BMO with these keywords!\n"
            + "0. hi [greet BMO]\n"
            + "1. bye [shut BMO down]\n"
            + "2. log [view task log]\n"
            + "3. add todo <task> [add todo task]\n"
            + "4. add deadline <task> /by <date> <time> [add deadline]\n"
            + "5. add event <event> /from <date> <time> /to <date> <time> [add event]\n"
            + "6. done <task number> [check task as done]\n"
            + "7. redo <task number> [uncheck task]\n"
            + "----------------------------------------------------------------------------------\n";

    public static final String hiPrint = "----------------------------------------------------------------------------------\n"
            + "    Good day! What can BMO help you with?\n"
            + "----------------------------------------------------------------------------------\n";

    public static final String byePrint = "----------------------------------------------------------------------------------\n"
            + "    Beep boop BMO shutting down...\n"
            + "----------------------------------------------------------------------------------\n";

    public static final String emptyLogPrint = "----------------------------------------------------------------------------------\n"
            + "    Wow! Your log is actually empty.\n"
            + "    Let's play mario kart right now!!\n"
            + "----------------------------------------------------------------------------------\n";

    public static class errorPrint {
        public static String general() {
            return "----------------------------------------------------------------------------------\n"
                    + "    BMO don't understand ;;;\n"
                    + "    You can refer to the command formats\n    that appear when you switch me on. ^^\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String outOfRange() {
            return "----------------------------------------------------------------------------------\n"
                    + "    The index you have provided\n"
                    + "    is out of BMO's range!\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String alreadyDone() {
            return "----------------------------------------------------------------------------------\n"
                    + "    Are you playing with BMO's feelings >:(\n"
                    + "    The task is already done silly!\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String alreadyUnDone() {
            return "----------------------------------------------------------------------------------\n"
                    + "    Did you really think that task was already done...\n"
                    + "    You don't have to redo what you haven't even completed!\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String noInt() {
            return "----------------------------------------------------------------------------------\n"
                    + "    BMO needs to know the index of the task!\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String erroneousAdd() {
            return "----------------------------------------------------------------------------------\n"
                    + "    If you want BMO to add a task to the log,\n"
                    + "    please use these formats:\n\n"
                    + "    add todo <task>\n"
                    + "    add deadline <task> /by <date> <time>\n"
                    + "    add event <task> /from <date> <time> /to <date> <time>\n\n"
                    + "    acceptable datetime format: dd/MM/yyyy HHmm (example: 5/12/2024 1830)\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String deadline() {
            return "----------------------------------------------------------------------------------\n"
                    + "    If you want BMO to record a deadline,\n"
                    + "    please input add deadline <task> /by <date> <time>\n\n"
                    + "    acceptable datetime format: dd/MM/yyyy HHmm (example: 5/12/2024 1830)\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String todo() {
            return "----------------------------------------------------------------------------------\n"
                    + "    If you want BMO to record a todo,\n"
                    + "    please input add todo <task>\n"
                    + "----------------------------------------------------------------------------------\n";
        }

        public static String event() {
            return "----------------------------------------------------------------------------------\n"
                    + "    If you want BMO to record an event,\n"
                    + "    please input add event <task> /from <date> <time> /to <date> <time>\n\n"
                    + "    acceptable datetime format: dd/MM/yyyy HHmm (example: 5/12/2024 1830)\n"
                    + "----------------------------------------------------------------------------------\n";
        }

    }


}
