package goblin.calendar;

import goblin.exception.OrkException;

public class Time {
    protected String unprocessed;
    protected String processed;
    protected boolean isAfternoon = false;

    public Time(String unprocessedTime) throws OrkException {
        this.unprocessed = unprocessedTime;
        processTime();
    }

    public void processTime() throws OrkException {
        if (unprocessed.isEmpty()) {
            return;
        }
        if (unprocessed.length() == 4) {
            int time = Integer.parseInt(unprocessed);
            int hour = time / 100;
            int minute = time % 100;
            if (hour > 23 || minute > 59) {
                throw new OrkException("PLEASE input a valid time.");
            }
            if (hour > 11) {
                hour = hour % 12;
                isAfternoon = true;
            }
            if (hour == 0) {
                hour = 12;
            }
            StringBuilder timing = new StringBuilder();
            timing.append(hour);
            if (minute != 0) {
                if (minute < 10) {
                    timing.append(":0" + minute);
                } else {
                    timing.append(":" + minute);
                }
            }
            processed = timing.toString();
        } else {
            throw new OrkException("There's no such time. You are the stupidest meat I've ever seen.");
        }
    }

    @Override
    public String toString() {
        if (processed == null) {
            return "";
        }
        if (isAfternoon) {
            return processed + "pm";
        } else {
            return processed + "am";
        }
    }
}
