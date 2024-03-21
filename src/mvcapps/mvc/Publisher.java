package mvcapps.mvc;

import java.util.LinkedList;
import java.util.List;


public class Publisher {
    private List<Subscriber> subscribers = new LinkedList<Subscriber>();

    public void notifySubscribers() {
        for(Subscriber s: subscribers) {
            s.update();
        }
    }
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }
    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }
}


