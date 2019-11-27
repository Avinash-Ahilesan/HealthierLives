package model;

import java.util.Observable;
import java.util.Observer;

public class NotificationSender implements Observer {

    //REQUIRES: that a person is passed in as arg
    //EFFECTS: outputs that the person is past target calories, and
    //         the last foods they ate
    @Override
    public void update(Observable o, Object arg) {
        Person p = (Person)arg;

        System.out.println(p.getName() + ", you have reached maximum calories!");
        System.out.println("Last food eaten is: " + p.getFoodsEaten());
    }
}
