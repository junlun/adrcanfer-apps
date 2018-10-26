package es.us.isa.androidtesting.inagraph;

import android.support.test.uiautomator.UiObjectNotFoundException;

import java.io.IOException;

import es.us.isa.androidtesting.inagraph.actions.Action;

/**
 * Created by japar on 18/03/2018.
 */

public class CloseAppAction extends Action {
    String appPackageName;
    public CloseAppAction(String appPackageName) {
        super(null);
        this.appPackageName=appPackageName;

    }

    @Override
    public void perform() throws UiObjectNotFoundException {
        try {
            Runtime.getRuntime().exec(new String[] {"am", "force-stop", appPackageName});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Close App "+this.appPackageName;
    }
}
