package es.us.isa.androidtesting.inagraph;


import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

/**
 * Created by japar on 17/03/2018.
 */

public class ButtonAction extends Action {

    public ButtonAction(UiObject button)
    {
        super(button);
    }

    public void perform() throws UiObjectNotFoundException {
        this.target.click();
    }
}
