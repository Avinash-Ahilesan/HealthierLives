package ui;


import model.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class HealthierLives {
    private ArrayList<Person> personList;
    private static final String PROFILE_PATH = "./data/todoListData.txt";

    public static void main(String[] args) {
        /*HealthierLives tracker = new HealthierLives();
        tracker.personList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        run(tracker, sc);*/

    }

    public static void saveProfileToFile(Person personsToSave) {

        try {
            FileOutputStream fileOut = new FileOutputStream(PROFILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(personsToSave);
            objectOut.close();
            System.out.println("person sucessfully written");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void run(HealthierLives tracker, Scanner sc) {
        String input = "";
        while (!input.equals("exit")) {
            System.out.println("Enter either create, get, list, or exit");
            input = sc.nextLine();
            if (input.equals("create")) {
                processCreate(tracker, sc);
            } else if (input.equals("get")) {
                processGetPerson(tracker, sc);
            } else if (input.equals("list")) {
                tracker.outputPersonList();
            } else if (input.equals("save")) {
                savePersonListToFile(tracker);
            } else if (input.equals("load")) {
                loadPersonListFromFile(tracker);
            } else if (input.equals("exit")) {
                break;
            }

        }
    }

    public static void processCreate(HealthierLives tracker, Scanner sc) {
        String input = "";
        System.out.println("Enter a name followed by an age");
        input = sc.nextLine();
        tracker.addPerson(input);
    }

    public static void processGetPerson(HealthierLives tracker, Scanner sc) {
        String input = "";
        System.out.println("Input a name to search for");
        input = sc.nextLine();
        tracker.getPersonFromList(input);
    }

    private static void savePersonListToFile(HealthierLives healthierLives) {

        for (Person person : healthierLives.personList) {
            person.save();
        }
    }

    private static void loadPersonListFromFile(HealthierLives healthierLives) {
        Person loadPerson = new Person("Loader", 0);
        ArrayList<Object> loader = new ArrayList<>();
        loader = loadPerson.load();
        for (Object personObj : loader) {
            healthierLives.personList.add((Person) personObj);
        }
    }

    //MODIFIES: this
    //EFFECTS: converts string into a person object, that's added to list
    public void addPerson(String personInfo) {
        Person newPerson = Person.parseString(personInfo);
        personList.add(newPerson);
        System.out.println("Added new person: ");
        printPersonInformation(newPerson);
    }

    //EFFECTS: outputs the people in the list
    public void outputPersonList() {
        for (Person p : personList) {
            System.out.println(p);
        }
    }

    //EFFECTS: finds the given person in the list, prints out their info
    public void getPersonFromList(String person) {
        for (Person p : personList) {
            if (p.getName().equals(person)) {
                printPersonInformation(p);
            }
        }
    }

    //EFFECTS: prints out the given persons information
    public void printPersonInformation(Person p) {
        System.out.println("Person's name is " + p.getName() + " with age " + p.getAge());
    }

}
