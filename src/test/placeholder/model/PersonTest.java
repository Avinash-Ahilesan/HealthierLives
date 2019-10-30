package placeholder.model;

import Exceptions.IncorrectParametersException;
import model.Ingredient;
import model.MealFood;
import model.Person;
import model.SimpleFood;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person;
    SimpleFood testFood1;
    Ingredient testIngredient1;
    Calendar testDate1;
    @BeforeEach
    private void setUp() {
        person = new Person("Avinash", 19);
        testDate1 = new Calendar.Builder().setDate(2019, 3,23).build();
        testFood1 = new SimpleFood("test",testDate1,25, 1);
        testIngredient1 = new Ingredient("Test Ingredient", 123, 1);
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

    @Test
    public void testFoodsEatenWithSimpleFood() {
        person.addFood(testFood1);
        assertEquals("test 25\n", person.getFoodsEaten());
    }

    @Test
    public void testFoodsEatenWithMeal() {
        MealFood meal = new MealFood("abc", testDate1, 1);
        meal.addIngredient(testIngredient1);
        person.addFood(meal);
        assertEquals("abc 123\n", person.getFoodsEaten());
    }


}
