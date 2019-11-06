package model;

import java.io.*;
import java.util.ArrayList;

public class LoadAndSave {
    private static String PATH = "./data/";

    //"todoListData.txt" = person
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
