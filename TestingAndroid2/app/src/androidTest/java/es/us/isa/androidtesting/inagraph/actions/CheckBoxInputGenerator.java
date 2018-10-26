package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import java.util.Random;

class CheckBoxInputGenerator extends InputGenerator {


    public void generateInput(UiObject object) throws UiObjectNotFoundException {

        if (new Random().nextBoolean()) {
            object.click();
        }
    }
}