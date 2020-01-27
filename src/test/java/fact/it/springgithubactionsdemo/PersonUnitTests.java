package fact.it.springgithubactionsdemo;

import fact.it.springgithubactionsdemo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PersonUnitTests {

    /**
     * Test of constructor and getters
     */
    @Test
    public void testConstructorAndGetters() {

        Person leonie = new Person("John", "Doe");
        assertEquals("John", leonie.getGivenName());
        assertEquals("Doe", leonie.getSurname());
        Person person = new Person();
        assertNull(person.getGivenName());
        assertNull(person.getSurname());
    }

    /**
     * Test of setGivenName method, of class Person.
     */
    @Test
    public void testSetVoornaam() {
        Person person = new Person();
        person.setGivenName("John");
        assertEquals("John", person.getGivenName());
    }

    /**
     * Test of setSurname method, of class Person.
     */
    @Test
    public void testSetFamilienaam() {
        Person person = new Person();
        person.setSurname("Doe");
        assertEquals("Doe", person.getSurname());
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        Person john = new Person("John", "Doe");
        assertEquals("DOE john", john.toString());
        Person alice = new Person("Alice", "Bozeman");
        assertEquals("BOZEMAN alice", alice.toString());
    }

}
