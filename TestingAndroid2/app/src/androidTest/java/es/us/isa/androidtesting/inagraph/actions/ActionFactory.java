package es.us.isa.androidtesting.inagraph.actions;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.us.isa.androidtesting.inagraph.Node;

public class ActionFactory {

    public static List<Action> createInputActions(Node node, UiDevice device) {
        TextInputGenerator generator = new TextInputGenerator();
        List<UiObject> inputTexts = ElementIdentifier.findElements(device, "android.widget.EditText");
        List<Action> result = new ArrayList<>();
        for (UiObject input : inputTexts) {
            result.add(new InputAction(input, generator));
            node.getControls().add(input);
        }
        return result;
    }

    public static List<Action> createButtonActions(Node node, UiDevice device) {
        List<Action> result = new ArrayList<>();
        List<UiObject> buttons = ElementIdentifier.findElements(device, "android.widget.Button");
        for (UiObject input : buttons) {
            result.add(new ButtonAction(input));
            node.getControls().add(input);
        }
        return result;
    }

    public static List<Action> createRadioActions(Node node, UiDevice device) {
        RadioButtonInputGenerator generator = new RadioButtonInputGenerator();
        List<Action> result = new ArrayList<>();
        List<UiObject> buttons = ElementIdentifier.findElements(device, "android.widget.RadioButton");
        for (UiObject input : buttons) {
            result.add(new RadioButtonAction(input, generator));
            node.getControls().add(input);
        }
        return result;
    }

    public static List<Action> createcheckBoxActions(Node node, UiDevice device) {

        CheckBoxInputGenerator generator = new CheckBoxInputGenerator();
        List<Action> result = new ArrayList<>();
        List<UiObject> buttons = ElementIdentifier.findElements(device, "android.widget.CheckBox");
        for (UiObject input : buttons) {
            result.add(new CheckBoxAction(input, generator));
            node.getControls().add(input);
        }
        return result;

    }

    public static List<Action> createSelectsActions(Node node, UiDevice device) {
        SpinnerInputGenerator generator = new SpinnerInputGenerator(device);
        List<Action> result = new ArrayList<>();
        List<UiObject> buttons = ElementIdentifier.findElements(device, "android.widget.Spinner");
        for (UiObject input : buttons) {
            result.add(new SpinnerAction(input, generator));
            node.getControls().add(input);
        }
        return result;
    }

    public static List<Action> createDatesActions(Node node, UiDevice device) {
        DatePickerInputGenerator generator = new DatePickerInputGenerator();
        List<Action> result = new ArrayList<>();
        List<UiObject> buttons = ElementIdentifier.findElements(device, "android.widget.DatePicker");
        for (UiObject input : buttons) {
            result.add(new DatePickerAction(input, generator));
            node.getControls().add(input);
        }
        return result;

    }
}
