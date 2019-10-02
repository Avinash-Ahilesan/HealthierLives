package model;

import java.io.*;
import java.util.ArrayList;

public class Person implements LoadableAndSaveable, Serializable {
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


    @Override
    public ArrayList<Object> load() {
        ArrayList<Object> personList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(PATH + "todoListData.txt"));
            ObjectInputStream reader = new ObjectInputStream(file);
            while (true) {
                try {
                    Object person = (Object)reader.readObject();
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
    public void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH + "todoListData.txt"));
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
}
