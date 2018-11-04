package es.us.isa.androidtesting.inagraph;
import android.support.test.uiautomator.UiObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;

import es.us.isa.androidtesting.inagraph.actions.Action;
import es.us.isa.androidtesting.inagraph.actions.InputGenerator;

public class Node {
    Set<UiObject> controls;
    List<Action> availableActions;
    Map<Action,Node> outputVetex;
    Map<UiObject,InputGenerator> inputGenerators;

    public Node(){
        controls=new HashSet<UiObject>();
        availableActions=new ArrayList<Action>();
        outputVetex=new HashMap<>();
        inputGenerators=new HashMap<UiObject, InputGenerator>();
    }


    public boolean isOutboundAction(Action action){
        return outputVetex.containsKey(action);
    }

    public Map<Action,Node> getOutputVertex(){
        return outputVetex;
    }

    public List<Action> getAvailableActions()
    {
        return availableActions;
    }

    public Set<UiObject> getControls() {
        return controls;
    }
}
