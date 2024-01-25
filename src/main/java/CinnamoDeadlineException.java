class CinnamoDeadlineException extends CinnamoException {
    @Override
    public String toString() {
        return "Hi! Please provide more details for the todo activity in " +
                "the correct format with \"/by\" followed by the specific deadline:) -- Cinnamo >.<";
    }
}
