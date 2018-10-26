package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.widget.RadioButton;

import java.util.Random;

/**
 * Created by japar on 18/03/2018.
 */

public class RadioButtonInputGenerator extends InputGenerator {


    public void generateInput(UiObject object) throws UiObjectNotFoundException {
        if (!object.getClassName().toLowerCase().contains("RadioGroup")) {
            throw new IllegalArgumentException("Tried to generate a Radio Button Action on a element that is not a RadioGroup: " +
                    object.getClassName());
        }

        Integer childToclick = new Random().nextInt(object.getChildCount() - 1);
        UiObject dataValue = object.getChild(new UiSelector().className(RadioButton.class.getName()).index(childToclick));
        dataValue.click();
    }
}
