package model;

import exceptions.IncorrectParametersException;
import model.food.Food;
import model.food.FoodContainer;
import model.trackers.TrackerManager;

import java.io.*;
import java.util.ArrayList;

public class Person implements Serializable {
    private String name;
    private int age;
    private int targetCalories;
    private FoodContainer foodEaten;
    private TrackerManager tm;

    public Person(String name, int age) {
        foodEaten = new FoodContainer();
        this.name = name;
        this.age = age;
        tm = new TrackerManager();
    }

    public void setTargetCalories(int target) {
        targetCalories = target;
    }

    public int getTargetCalories() {
        return targetCalories;
    }


    public void addFood(Food food) {
        foodEaten.addFood(food);
    }

    public String getFoodsEaten() {
        return foodEaten.getFoodsEaten();
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
    public static Person parseString(String personString) throws IncorrectParametersException {
        String[] personParams = personString.split("\\s+");
        if (personParams.length < 2) {
            throw new IncorrectParametersException();
        }
        return new Person(personParams[0], Integer.parseInt(personParams[1]));
    }


    //EFFECTS: returns true if two objects are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        if (person.name.equals(this.name) && person.age == this.age
                && person.foodEaten.equals(foodEaten) && targetCalories == person.targetCalories) {
            return true;
        }
        return false;
    }
}
