package model;

import exceptions.IncorrectParametersException;
import javafx.collections.ObservableList;
import model.food.Food;
import model.food.FoodContainer;
import model.trackers.TrackerManager;
import ui.controllers.MainUIController;

import java.io.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Person extends Observable implements Serializable {
    private String name;
    private int age;
    private int targetCalories;
    private FoodContainer foodEaten;
    private TrackerManager tm;

    public Person(String name, int age, int targetCalories) {
        addObserver(new NotificationSender());
        foodEaten = new FoodContainer();
        this.name = name;
        this.age = age;
        tm = new TrackerManager();
        this.targetCalories = targetCalories;
    }


    public void setObserver(Observer observer) {
        addObserver(observer);

    }

    public void setTargetCalories(int target) {
        targetCalories = target;
    }

    public int getTargetCalories() {
        return targetCalories;
    }


    public void addFood(Food food) {
        foodEaten.addFood(food);
        System.out.println(foodEaten.getCalorieTotal());
        if (isPastTargetCalories()) {
            setChanged();
            notifyObservers(this);
        }
    }

    public String getFoodsEaten() {
        return foodEaten.getFoodsEaten();
    }

    public List<Food> getFoodsEaten(TimeStamp timeStamp) {
        return foodEaten.getFoodsOnDate(timeStamp);
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
        return new Person(personParams[0], Integer.parseInt(personParams[1]), 0);
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

    public int getTotalCalories() {
        return foodEaten.getCalorieTotal();
    }

    public boolean isPastTargetCalories() {
        if (foodEaten.getCalorieTotal() > targetCalories) {
            return true;
        }
        return false;
    }

}
