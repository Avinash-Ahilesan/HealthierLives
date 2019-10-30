package model;

import Exceptions.IncorrectParametersException;

import java.io.*;
import java.util.ArrayList;

public class Person implements LoadableAndSaveable {
    private String name;
    private int age;
    private int targetCalories;
    private FoodContainer foodEaten;

    public Person(String name, int age) {
        foodEaten = new FoodContainer();
        this.name = name;
        this.age = age;

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


    @Override
    public ArrayList<Object> load() {
        ArrayList<Object> personList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(PATH + "todoListData.txt"));
            ObjectInputStream reader = new ObjectInputStream(file);
            while (true) {
                try {
                    Object person = reader.readObject();
                    personList.add(person);
                } catch (Exception ex) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("could not read");
        }
        return personList;
    }

    @Override
    public void save(boolean append) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH + "todoListData.txt"), append);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } catch (IOException e) {
            System.out.println("IO Exception occured");
        }
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
