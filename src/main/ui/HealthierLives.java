package ui;


import Exceptions.IncorrectParametersException;
import Exceptions.NoSuchPersonException;
import model.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class HealthierLives {
    private ArrayList<Person> personList;
    private static final String PROFILE_PATH = "./data/todoListData.txt";

    public static void main(String[] args) {
        HealthierLives tracker = new HealthierLives();
        tracker.personList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        run(tracker, sc);

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
        System.out.println("Welcome to HealthTracker!");
        System.out.println("Enter either create, get, list, save, load, or exit");
        String input = sc.nextLine();
        while (!input.equals("exit")) {
            try {
                runHelper(input, tracker);
            } catch (NoSuchPersonException e) {
                System.out.println("No such person found!");
            } catch (IncorrectParametersException e) {
                System.out.println("You forgot a parameter!");
            } catch (NumberFormatException e) {
                System.out.println("You entered a string somewhere you should've put a number!");
            } finally {
                System.out.println("Enter either create, get, list, save, load, or exit");
                input = sc.nextLine();
            }
        }
    }

    private static void runHelper(String input, HealthierLives tracker)
            throws IncorrectParametersException, NoSuchPersonException {
        Scanner sc = new Scanner(System.in);
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
        }
    }


    public static void processCreate(HealthierLives tracker, Scanner sc) throws IncorrectParametersException {
        String input = "";
        System.out.println("Enter a name followed by an age");
        input = sc.nextLine();
        tracker.addPerson(input);
    }

    public static void processGetPerson(HealthierLives tracker, Scanner sc)
            throws NoSuchPersonException, IncorrectParametersException {
        System.out.println("Input a name to search for");
        String input = sc.nextLine();
        Person p = tracker.getPersonFromList(input);
        System.out.println("Would you like to add a food, or list the foods eaten? Enter add/list");
        input = sc.nextLine();
        if (input.equals("add")) {
            helpAddFood(p, sc);
        }
        if (input.equals("list")) {
            System.out.println(p.getFoodsEaten());
        }


    }


    private static void helpAddFood(Person p, Scanner sc) throws IncorrectParametersException {
        System.out.println("Enter meal or simple food");
        String input = sc.nextLine();
        if (input.equals("meal")) {
            processAddMeal(p, sc);
        } else if (input.equals("food")) {
            processAddSimpleFood(p, input, sc);
        }
    }

    public static void processAddMeal(Person p, Scanner sc) throws IncorrectParametersException {
        System.out.println("enter meal name");
        String input = sc.nextLine();
        MealFood food = new MealFood(input, new Calendar.Builder().build(), 1);
        System.out.println("input ingredients names followed by calorie and quantity eaten"
                + " one by one, type end when done");
        input = sc.nextLine();
        while (!input.equals("end")) {
            String[] foodInput = input.split(" ");
            if (foodInput.length < 2) {
                throw new IncorrectParametersException();
            }
            food.addIngredient(new Ingredient(foodInput[0], Integer.parseInt(foodInput[1]),
                    Integer.parseInt(foodInput[2])));
            input = sc.nextLine();
        }
        p.addFood(food);
    }

    public static void processAddSimpleFood(Person p, String input, Scanner sc) throws IncorrectParametersException {
        System.out.println("Enter food name, calories, and quantity");
        input = sc.nextLine();
        String[] foodInput = input.split(" ");
        if (foodInput.length < 2) {
            throw new IncorrectParametersException();
        }
        p.addFood(new SimpleFood(foodInput[0], new Calendar.Builder().build(), Integer.parseInt(foodInput[1]), 1));
    }

    private static void savePersonListToFile(HealthierLives healthierLives) {

        for (int i = 0; i < healthierLives.personList.size(); i++) {
            if (i == 0) {
                healthierLives.personList.get(i).save(false);

            } else {
                healthierLives.personList.get(i).save(true);
            }
        }
    }

    private static void loadPersonListFromFile(HealthierLives healthierLives) {
        LoadableAndSaveable loadPerson = new Person("Loader", 0);

        ArrayList<Object> loader = loadPerson.load();
        for (Object personObj : loader) {
            healthierLives.personList.add((Person) personObj);
        }
    }

    //MODIFIES: this
    //EFFECTS: converts string into a person object, that's added to list
    public void addPerson(String personInfo) throws IncorrectParametersException {
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
    public Person getPersonFromList(String person) throws NoSuchPersonException {
        for (Person p : personList) {
            if (p.getName().equals(person)) {
                printPersonInformation(p);
                return p;
            }
        }
        throw new NoSuchPersonException();
    }

    //EFFECTS: prints out the given persons information
    public void printPersonInformation(Person p) {
        System.out.println("Person's name is " + p.getName() + " with age " + p.getAge());
    }

}
