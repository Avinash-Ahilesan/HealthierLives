package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class HeartRateTracker implements Tracker, LoadableAndSaveable {

    private ArrayList<HeartRateRecording> heartRateRecords = new ArrayList<>();

    public void addHeartRate(Calendar date, int bpm) {
        heartRateRecords.add(new HeartRateRecording(date, bpm));
    }

    public void addRecording(TrackerRecording recording) {
        heartRateRecords.add((HeartRateRecording) recording);
    }

    @Override
    public void generateTable() {
        //TODO: Implement method
    }

    @Override
    public HeartRateRecording getLastRecording() {

        return heartRateRecords.get(heartRateRecords.size() - 1);
    }


    @Override
    public String toString() {
        return heartRateRecords.toString();
    }

    @Override
    public ArrayList<Object> load() {
        ArrayList<Object> recordList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(PATH + "heartRateRecordingSave.txt"));
            ObjectInputStream reader = new ObjectInputStream(file);
            while (true) {
                try {
                    Object record = reader.readObject();
                    System.out.println(record);
                    recordList.add(record);
                } catch (Exception ex) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("could not read");
        }
        return recordList;
    }

    @Override
    public void save(boolean append) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    new File(PATH + "heartRateRecordingSave.txt"), append);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
