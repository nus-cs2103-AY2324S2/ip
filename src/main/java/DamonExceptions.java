public class DamonExceptions {
    static void test(String inputString) throws WrongInputException, NoDescriptionException,
            FormatException, NoDeadLineException, NoDescriptionDeadlineException,
            TooManyDeadlineException {
        if (!(inputString.startsWith("todo")
                || inputString.startsWith("deadline") || inputString.startsWith("event"))) {
            throw new WrongInputException();
        }
        String[] firstStr = inputString.split(" ");
        String firstWord = firstStr[0];

        if (inputString.startsWith("todo")) {
            if (firstStr.length == 1) {
                if (firstWord.equals("todo")) {
                    throw new NoDescriptionException();
                } else {
                    throw new WrongInputException();
                }
            }

            if (inputString.substring(4).isBlank()) {
                throw new NoDescriptionException();
            }

        } else if (inputString.startsWith("deadline")) {
            if (firstStr.length == 1) {
                if (firstWord.equals("deadline")) {
                    throw new NoDescriptionDeadlineException();
                } else {
                    throw new WrongInputException();
                }
            }

            if (inputString.substring(8).isBlank()) {
                throw new NoDescriptionDeadlineException();
            }

            String[] deadlineStr = inputString.split(" /by ");
            boolean isDescription = !(deadlineStr[0].equals("deadline")
                    || deadlineStr[0].substring(8).isBlank());

            if (deadlineStr.length == 1) {
                if (! isDescription) {
                    throw new NoDescriptionDeadlineException();
                }
                throw new NoDeadLineException();
            } else if (deadlineStr.length > 2) {
                throw new TooManyDeadlineException();
            } else {
                if (deadlineStr[1].isBlank()) {
                    throw new NoDeadLineException();
                }
                if (deadlineStr[0].equals("deadline")
                        || deadlineStr[0].substring(8).isBlank()) {
                    throw new NoDescriptionException();
                }
            }
        }
    }
}
