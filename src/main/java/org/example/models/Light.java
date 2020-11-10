package org.example.models;

import org.example.onToggleListener;

public class Light extends Thing {
    public interface OnLightChangedListener {
        void onLightChanged(Light light);
    }

    private OnLightChangedListener lightChangedListener;
    private boolean isLightOn = false;

    @Override
    public String getTypeName() {
        return "Light";
    }

    @Override
    public String getDescription() {
        return "Light is on="+isLightOn;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public void setLightOn(boolean lightOn) {
        if (state == State.UNREACHABLE) {
            return;
        }
        isLightOn = lightOn;
        if (lightChangedListener != null) {
            lightChangedListener.onLightChanged(this);
        }
    }

    public void setLightChangedListener(OnLightChangedListener lightChangedListener) {
        this.lightChangedListener = lightChangedListener;
    }
}
