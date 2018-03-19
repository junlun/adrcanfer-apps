package es.us.isa.androidtesting.inagraph;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiObjectNotFoundException;

/**
 * Created by japar on 18/03/2018.
 */

public class StartAppAction extends Action {

    String appPackage;

    public StartAppAction(String appPackage) {
        super(null);
        this.appPackage=appPackage;
    }

    @Override
    public void perform() throws UiObjectNotFoundException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackage);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public String toString() {
        return "Start App "+this.appPackage;
    }
}
