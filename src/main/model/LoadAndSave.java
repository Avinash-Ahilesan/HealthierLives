package model;

import java.io.*;
import java.util.ArrayList;

public class LoadAndSave {
    private static String PATH = "./data/";


    //"todoListData.txt" = person
    //MODIFIES: a file with name fileName
    //EFFECTS: saves the object data for the object given to a file
    //         with name fileName, appending if set true by user
    public static void save(boolean append, String fileName, Object obj) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH + fileName), append);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } catch (IOException e) {
            System.out.println("IO Exception occured");
        }
    }

    //REQUIRES: the fileName file should exist
    //MODIFIES: text file named filenAme
    //EFFECTS: returns a list of objects that were in the file
    public static ArrayList<Object> load(String fileName) {
        ArrayList<Object> personList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(PATH + fileName));
            ObjectInputStream reader = new ObjectInputStream(file);
            while (true) {
                try {
                    Object obj = reader.readObject();
                    personList.add(obj);
                } catch (Exception ex) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("could not read");
        }
        return personList;
    }
}
