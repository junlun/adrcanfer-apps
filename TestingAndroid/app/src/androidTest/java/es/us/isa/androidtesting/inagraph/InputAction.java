package es.us.isa.androidtesting.inagraph;

import android.renderscript.ScriptGroup;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;


public class InputAction extends Action {
    InputGenerator inputGenerator;
    public InputAction(UiObject target, InputGenerator generator){
        super(target);
        this.inputGenerator=generator;
    }

    public void perform() throws UiObjectNotFoundException
    {
        inputGenerator.generateInput(target);
    }

    @Override
    public String toString() {
        return "generate input";
    }
}
