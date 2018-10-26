package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ElementIdentifier {

    public static List<UiObject> findElements(UiDevice device, String finder) {
        List<UiObject> result = new ArrayList<>();
        BySelector sel = resolveSelector(finder);
        List<UiObject2> botones = device.findObjects(sel);
        UiSelector selector = null;
        UiObject button = null;
        for (UiObject2 btn : botones) {
            selector = new UiSelector().text(btn.getText());
            button = device.findObject(selector);
            result.add(button);
        }
        return result;
    }

    private static BySelector resolveSelector(String finder) {
        BySelector result = null;
        finder = finder.substring(finder.lastIndexOf(".") + 1);
        if (finder.equalsIgnoreCase("button")) {
            result = By.clazz(Button.class);
        } else if (finder.equalsIgnoreCase("EditText")) {
            result = By.clazz(EditText.class);
        } else if (finder.equalsIgnoreCase("RadioButton")) {
            result = By.clazz(RadioGroup.class);
        } else if (finder.equalsIgnoreCase("CheckBox")) {
            result = By.clazz(CheckBox.class);
        } else if (finder.equalsIgnoreCase("Spinner")) {
            result = By.clazz(Spinner.class);
        } else if (finder.equalsIgnoreCase("DatePicker")) {
            result = By.clazz(DatePicker.class);
        }
        return result;
    }
}
