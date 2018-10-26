package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.UiObject;

class CheckBoxAction extends InputAction {


    public CheckBoxAction(UiObject target, InputGenerator generator) {
        super(target, generator);
    }

    @Override
    public String toString() {
        return "clicking on check boxes";
    }
}
