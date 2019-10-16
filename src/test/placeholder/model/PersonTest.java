package placeholder.model;

import model.Person;
import org.junit.jupiter.api.*;
import ui.IncorrectParametersException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person;

    @BeforeEach
    private void setUp() {
        person = new Person("Avinash", 19);
    }

    @Test
    public void testGetNameAndSetName() {
        assertEquals("Avinash", person.getName());
        person.setName("Blah");
        assertEquals("Blah", person.getName());
    }


    @Test
    public void testGetAgeAndSetAge() {
        assertEquals(19, person.getAge());
        person.setAge(35);
        assertEquals(35, person.getAge());
        person.setAge(100);
        assertEquals(100, person.getAge());
    }

    @Test
    public void testToString() {
        assertEquals("Avinash", person.getName());
        assertEquals(19, person.getAge());
        assertEquals("Avinash 19", person.toString());
    }

    @Test
    public void testParseStringNoException() {
        try {
            Person person = Person.parseString("Avinash 25"); // 1 space
            assertEquals("Avinash", person.getName());
            assertEquals(25, person.getAge());

            person = Person.parseString("Bleh  16"); // 2 spaces
            assertEquals("Bleh", person.getName());
            assertEquals(16, person.getAge());
        } catch (IncorrectParametersException e){
            fail();
        }

        /*person = Person.parseString("Avinash24");
        assertEquals("Avinash", person.getName());
        assertEquals(24, person.getAge());*/
    }

    @Test
    public void testParseStringExceptionThrown() {
        try {
            Person person = Person.parseString("Avinash25"); // 1 space
            fail();
            assertEquals("Avinash", person.getName());
            assertEquals(25, person.getAge());
        } catch (IncorrectParametersException e){
            System.out.println("Success!");
        }

    }

    @Test
    public void testSaveAndLoadSinglePerson() {
        person.save(false);

        ArrayList<Object> loadedPersonList = person.load();
        Person loadedPerson = (Person) loadedPersonList.get(0);

        assertEquals(person, loadedPerson);

    }

    @Test
    public void testEqualsTrue(){
        Person person2 = new Person(person.getName(), person.getAge());
        assertTrue(person.equals(person2));
    }

    @Test
    public void testEqualsFalse(){
        Person person2 = new Person(person.getName(),32);
        assertFalse(person.equals(person2));
    }


}
