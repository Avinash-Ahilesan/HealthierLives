package model;

public class Person {
    private String name;
    private int age;
    private int targetCalories;
    private FoodContainer foodEaten;

    public Person(String name, int age) {
        foodEaten = new FoodContainer();
        this.name = name;
        this.age = age;
    }

    //EFFECTS: Returns the name and age of the person
    @Override
    public String toString() {
        return name + " " + age;
    }


    //EFFECTS: returns the name of the Person
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: sets the Persons name
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: returns the age of the person
    public int getAge() {
        return age;
    }

    //REQUIRES: Any integer greater than 0
    //MODIFIES: this
    //EFFECTS: Sets the age of this to the parameter
    public void setAge(int age) {
        this.age = age;
    }

    //REQUIRES: Non-empty string containing person and age,
    //          separated by at least one space
    //EFFECTS: returns a new person with name and age
    //         contained in string
    public static Person parseString(String personString) {
        String[] personParams = personString.split("\\s+");
        return new Person(personParams[0], Integer.parseInt(personParams[1]));
    }
}
