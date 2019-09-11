package ui;


import model.Patient;

public class DrPhone {
    public static void main(String[] args) {
        Patient p = new Patient("Jason", 25);
        welcome();
        printPatientInformation(p);
    }

    public static void welcome() {
        System.out.println("Welcome to the Dr Phone application!");
    }

    public static void printPatientInformation(Patient p) {
        System.out.println("Patient name is " + p.getName() + " with age " + p.getAge());
    }

}
