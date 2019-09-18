package model;

public class Person {
    private String name;
    private int age;
    private FoodContainer foodEaten;

    public Person(String name, int age) {
        foodEaten = new FoodContainer();
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return name + " " + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Person parseString(String personString) {
        String[] personParams = personString.split(" ");
        return new Person(personParams[0], Integer.parseInt(personParams[1]));
    }
}
