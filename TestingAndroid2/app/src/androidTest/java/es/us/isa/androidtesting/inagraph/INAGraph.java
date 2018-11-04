package es.us.isa.androidtesting.inagraph;

import android.support.test.uiautomator.UiObjectNotFoundException;

import java.util.List;
import java.util.Set;

import es.us.isa.androidtesting.inagraph.actions.Action;

/**
 * Created by japar on 17/03/2018.
 */

public class INAGraph {
    Node currentNode;
    Node initialNode;

    public INAGraph(){this(new Node());}

    public INAGraph(Node initialNode){
        this.currentNode=initialNode;
        this.initialNode=currentNode;
    }

    public Node getCurrentNode(){
        return currentNode;
    }

    public Node getInitialNode(){
        return initialNode;
    }

    public List<Action> getAvailableActions(){
        return currentNode.getAvailableActions();
    }

    public Set<Action> getOutboundActions(){
        return currentNode.getOutputVertex().keySet();
    }

    public void performAction(Action a){
        try {
            a.perform();
            if (currentNode.isOutboundAction(a)) {
                currentNode = currentNode.getOutputVertex().get(a);
            }
        }catch (UiObjectNotFoundException e){
            System.out.println("UI Object not found: " +a.getTarget()+" - "+e.getMessage());
        }
    }

    public void reset(){
        currentNode=initialNode;
    }
}
