package grizzly.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

public class ContactTest {
    @Test
    public void testContact() {
        Contact c = new Contact("Johan", "dess@gmail.com", 123132132);
        assertEquals(c.toString(), ("Johan | dess@gmail.com | 123132132"));
    }

    @Test
    public void testContact2() {
        Contact c = new Contact("Madeline", "madeline@hotmail.com", 91824901);
        assertEquals(c.toString(), ("Madeline | madeline@hotmail.com | 91824901"));
    }

    @Test
    public void testContactParse() {
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "Johan");
        testMap.put("email", "joh@gmail.com");
        testMap.put("number", "123132132");
        try {
            Contact c = Contact.contactParse(testMap);
            assertEquals(c.toString(), ("Johan | joh@gmail.com | 123132132"));
        } catch (Exception e) {
            fail();
        }
    }
}
