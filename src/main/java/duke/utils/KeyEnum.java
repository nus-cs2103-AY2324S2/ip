package duke.utils;

/**
 * Enumerate class represents command keywords.
 */
public enum KeyEnum {
	EXITKEY("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"), EVENT("event"), DEADLINE("deadline"), TODO("todo"), DELETE("delete"), INVALID("invalid");
	private String detail;

    /**
     * Initializes a keyword with detail.
     * @param detail Meaning of keyword.
     */
    KeyEnum(String detail) {
        this.detail = detail;
    }

	@Override
	public String toString() {
		return this.detail;
	}
}
