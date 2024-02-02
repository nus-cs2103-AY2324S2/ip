public class WrongDateOrderingException extends DukeException{

        public WrongDateOrderingException() {
            super();
        }

        @Override
        public String getMessage() {
            return super.getMessage() + "the 'to' date is after the 'from' date.";
        }
}
