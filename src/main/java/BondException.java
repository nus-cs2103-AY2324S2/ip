public class BondException extends Exception {

  private enum ExceptionType {
    EMPTY_DESCRIPTION, INVALID_COMMAND_TYPE, MISSING_INDEX, EXTRA_DETAILS, INVALID_INDEX
  }

  private BondException(String message) {
    super(message);
  }

  public static void raiseException(String taskName, String exceptionType) throws BondException {

    String message = "";

    if (exceptionType.equals(ExceptionType.INVALID_COMMAND_TYPE.toString())) {
      message = "WHAT do you MEAN???????????";
    } else if (exceptionType.equals(ExceptionType.EMPTY_DESCRIPTION.toString())) {
      message = "Are you for REAL??? No info for a" + taskName;
    } else if (exceptionType.equals(ExceptionType.MISSING_INDEX.toString())) {
      message = "WHY did you not give me an INDEX to" + taskName + " a task!!!";
    } else if (exceptionType.equals(ExceptionType.EXTRA_DETAILS.toString())) {
      message = "I see, you are SO EXTRA and saying" + taskName + " needs MORE!!!";
    } else if (exceptionType.equals(ExceptionType.INVALID_INDEX.toString())) {
      message = "WHY did you not give me a PROPER INDEX to" + taskName + " a task!!!";
    }

    throw new BondException(message);
  }

}
