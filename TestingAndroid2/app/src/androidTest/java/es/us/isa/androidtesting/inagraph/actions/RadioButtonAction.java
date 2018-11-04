package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.UiObject;

class RadioButtonAction extends InputAction {


    public RadioButtonAction(UiObject target, InputGenerator generator) {
        super(target, generator);
    }

    @Override
    public String toString() {
        return "click on radio button";
    }

}
