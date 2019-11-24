package placeholder.model;

import exceptions.IncorrectParametersException;
import model.LoadAndSave;
import model.TimeStamp;
import model.food.Ingredient;
import model.food.MealFood;
import model.Person;
import model.food.SimpleFood;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person;
    SimpleFood testFood1;
    Ingredient testIngredient1;
    TimeStamp testDate1;

    @BeforeEach
    private void setUp() {
        person = new Person("Avinash", 19, 0);
        testDate1 = new TimeStamp(2019, 3, 23);
        testFood1 = new SimpleFood("test", testDate1, 25, 1);
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
        } catch (IncorrectParametersException e) {
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
        } catch (IncorrectParametersException e) {
            System.out.println("Success!");
        }

    }

    @Test
    public void testSaveAndLoadSinglePerson() {
        LoadAndSave.save(false, "todoListData.txt",person);

        ArrayList<Object> loadedPersonList = LoadAndSave.load( "todoListData.txt");
        Person loadedPerson = (Person) loadedPersonList.get(0);

        assertEquals(person, loadedPerson);

    }

    @Test
    public void testEqualsTrue() {
        Person person2 = new Person(person.getName(), person.getAge(), 0);
        assertTrue(person.equals(person2));
    }

    @Test
    public void testEqualsFalse() {
        Person person2 = new Person(person.getName(), 32, 0);
        assertFalse(person.equals(person2));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(person.equals(null));
    }

    @Test
    public void testEqualsIsThis() {
        assertTrue(person.equals(person));
    }
    @Test
    public void testFoodsEatenWithSimpleFood() {
        person.addFood(testFood1);
        assertEquals("test 25 23/3/2019\n", person.getFoodsEaten());
    }

    @Test
    public void testFoodsEatenWithMeal() {
        MealFood meal = new MealFood("abc", testDate1, 1);
        meal.addIngredient(testIngredient1);
        person.addFood(meal);
        assertEquals("abc 123\n", person.getFoodsEaten());
    }

    @Test
    public void testTargetCaloriesGetAndSet() {
        person.setTargetCalories(100);
        assertEquals(100, person.getTargetCalories());
    }

    @Test
    public void testNotifier() {
        person.setTargetCalories(100);
        person.addFood(new SimpleFood("Jack", new TimeStamp(2019, 2, 3), 150,1));

    }

    @Test
    public void testOverCalories() {
        assertFalse(person.isPastTargetCalories());
    }

}
