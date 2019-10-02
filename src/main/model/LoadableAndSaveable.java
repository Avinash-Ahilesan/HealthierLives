package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public interface LoadableAndSaveable {
    String PATH = "./data/";

    ArrayList<Object> load();

    void save();
}
