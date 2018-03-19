package es.us.isa.androidtesting.inagraph;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;


public abstract class  Action {

    protected UiObject target;
    public Action(UiObject target){
        this.target=target;
    }

    public abstract void perform() throws UiObjectNotFoundException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;

        Action action = (Action) o;

        boolean result=false;
        try {
            result = target.getClassName().equals(action.target.getClassName()) && target.getText().equals(action.target.getText());
        }catch(UiObjectNotFoundException e){
          e.printStackTrace();
        }
        return result;
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    public UiObject getTarget() {
        return target;
    }

    public abstract String toString();
}
