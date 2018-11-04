package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.widget.RadioButton;

import java.util.Random;

class SpinnerInputGenerator extends InputGenerator {
    private final UiDevice device;

    public SpinnerInputGenerator(UiDevice device) {
        super();
        this.device = device;
    }

    public void generateInput(UiObject object) throws UiObjectNotFoundException {
        //first we click in spinner so options appear
        object.click();

        //Now we wait for options to appear

        //TODO: How to find elements inside select??
//        device.wait(Until.findObject(By.))
//        Integer childToclick = new Random().nextInt(object.getChildCount() - 1);
//        UiObject dataValue = object.getChild(new UiSelector().className(RadioButton.class.getName()).index(childToclick));
//        dataValue.click();
    }
}