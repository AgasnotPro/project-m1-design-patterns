package org.example;

import org.example.models.Light;
import org.example.models.Thing;

import java.util.ArrayList;
import java.util.List;

public class HomeSystem implements Light.OnLightChangedListener {
    public enum State {
        ON,
        OFF,
    }
    //final implique ici que notre list ne sera jamais null après avoir été initialisée la première fois
    private final List<Thing> things;
    private final SystemLogger logger;
    private State state;

    public HomeSystem(SystemLogger logger) {
        this.logger = logger;
        this.things = new ArrayList<>();
        this.state = State.ON;
    }


    public boolean addThing(Thing thing){
        return things.add(thing);
    }

    public List<Thing> getThings() {
        return things;
    }

    @Override
    public void onLightChanged(Light light) {
        String message = "HomeSystem - Light "+light.getName()+" updated. Light on="+light.isLightOn();
        logger.addLog(message);
    }


    public void toggleAllLights(boolean isLightOn) {
        if (state == State.OFF) {
            return;
        }
        for (Light l:getLights()) {
            l.setLightOn((isLightOn));
        }
    }

    public List<Light> getLights() {
        List<Light> list = new ArrayList<>();

        for (Thing t : getThings()) {
            if (t instanceof Light) {
                list.add((Light)t);
            }
        }
        return list;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
