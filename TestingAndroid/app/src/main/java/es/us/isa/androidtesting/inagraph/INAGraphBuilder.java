package es.us.isa.androidtesting.inagraph;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.uiautomator.UiSelector.*;


public class INAGraphBuilder {

    private static INAGraphBuilder _instance;

    private INAGraphBuilder(){}

    public static INAGraphBuilder getInstance(){
        if(_instance==null)
            _instance=new INAGraphBuilder();
        return _instance;
    }

    public INAGraph build(UiDevice device) throws UiObjectNotFoundException{
        Node node=buildNode(device);
        return new INAGraph(node);
    }

    public Node buildNode(UiDevice device) throws UiObjectNotFoundException{
        Node node=new Node();
        createActions(node,device);
        buildVertex(node,device);
        return node;
    }

    public void createActions(Node node, UiDevice device) throws UiObjectNotFoundException{
        createInputActions(node, device);
        createButtonActions(node, device);

    }

    public void createInputActions(Node node,UiDevice device) throws UiObjectNotFoundException{
        TextInputGenerator generator=new TextInputGenerator();
        List<UiObject> inputTexts=findInputTexts(device);
        for(UiObject input:inputTexts){
            node.getAvailableActions().add(new InputAction(input,generator));
            node.getControls().add(input);
        }
    }

    private List<UiObject> findInputTexts(UiDevice device) throws UiObjectNotFoundException
    {
        List<UiObject> result=new ArrayList<>();
        UiSelector selector=new UiSelector().className(EditText.class);
        UiCollection collection=new UiCollection(selector);
        for(int i=0;i<collection.getChildCount(selector);i++)
            result.add(collection.getChildByInstance(selector,i));
        return result;
    }

    public void createButtonActions(Node node,UiDevice device)  throws UiObjectNotFoundException{
        List<UiObject> buttons=findButtons(device);
        for(UiObject input:buttons){
            node.getAvailableActions().add(new ButtonAction(input));
            node.getControls().add(input);
        }
    }

    public List<UiObject> findButtons(UiDevice device) throws UiObjectNotFoundException {
        List<UiObject> result=new ArrayList<>();
        UiSelector selector=new UiSelector().className(Button.class);
        UiCollection collection=new UiCollection(selector);
        for(int i=0;i<collection.getChildCount(selector);i++)
            result.add(collection.getChildByInstance(selector,i));
        return result;
    }

    public void buildVertex(Node node,UiDevice device) throws UiObjectNotFoundException {
        for(Action a:node.getAvailableActions()){
            a.perform();
            if(!isSameNode(node,device)){
                Node nextNode=buildNode(device);
                node.getOutputVertex().put(a,nextNode);
            }
        }
    }

    /**
     * Clearly, this implementation is simplistic and can lead to inconsistent behavious when applied
     * to complex user interfaces, but for a first approach it could be a starting point.
     * @param currentNode
     * @param device
     * @return Whether we are in a new UI state or not.
     * @throws UiObjectNotFoundException
     */

    public boolean isSameNode(Node currentNode, UiDevice device) throws UiObjectNotFoundException {
        List<UiObject> inputTexts=findInputTexts(device);
        List<UiObject> buttons=findButtons(device);
        return currentNode.getControls().containsAll(inputTexts) && currentNode.getControls().containsAll(buttons)
                && currentNode.getControls().size()==(inputTexts.size()+buttons.size());

    }

}
