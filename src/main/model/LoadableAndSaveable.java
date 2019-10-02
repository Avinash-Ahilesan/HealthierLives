package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public interface LoadableAndSaveable extends Serializable {
    String PATH = "./data/";

    //EFFECTS: Returns an ArrayList of objects loaded from a file
    ArrayList<Object> load();

    //EFFECTS: Saves objects content to a file
    void save(boolean append);
}
