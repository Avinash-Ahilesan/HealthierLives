package ui;


import model.Person;

import java.util.*;

public class HealthTracker {
    private ArrayList<Person> personList;

    public static void main(String[] args) {
        /*HealthTracker tracker = new HealthTracker();
        tracker.personList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        run(tracker, sc);*/

    }

    public static void run(HealthTracker tracker, Scanner sc) {
        String input = "";
        while (!input.equals("exit")) {
            System.out.println("Enter either create, get, list, or exit");
            input = sc.nextLine();
            if (input.equals("create")) {
                System.out.println("Enter a name followed by an age");
                input = sc.nextLine();
                tracker.addPerson(input);
            } else if (input.equals("get")) {
                System.out.println("Input a name to search for");
                input = sc.nextLine();
                tracker.getPersonFromList(input);
            } else if (input.equals("list")) {
                tracker.outputPersonList();
            } else if (input.equals("exit")) {
                break;
            }
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
    public  void printPersonInformation(Person p) {
        System.out.println("Person's name is " + p.getName() + " with age " + p.getAge());
    }
}
