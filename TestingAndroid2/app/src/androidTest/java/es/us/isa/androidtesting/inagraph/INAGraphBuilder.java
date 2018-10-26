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

import es.us.isa.androidtesting.inagraph.actions.Action;
import es.us.isa.androidtesting.inagraph.actions.ActionFactory;
import es.us.isa.androidtesting.inagraph.actions.ButtonAction;
import es.us.isa.androidtesting.inagraph.actions.InputAction;
import es.us.isa.androidtesting.inagraph.actions.TextInputGenerator;


public class INAGraphBuilder {

    private static INAGraphBuilder _instance;

    private INAGraphBuilder() {
    }

    public static INAGraphBuilder getInstance() {
        if (_instance == null)
            _instance = new INAGraphBuilder();
        return _instance;
    }

    public INAGraph build(UiDevice device, String appName) throws UiObjectNotFoundException {
        startApp(device, appName);
        Node node = buildNode(device);
        closeApp(device, appName);
        return new INAGraph(node);
    }

    private void closeApp(UiDevice device, String appPackage) throws UiObjectNotFoundException {
        CloseAppAction action = new CloseAppAction(appPackage);
        action.perform();
    }

    private void startApp(UiDevice device, String appPackage) throws UiObjectNotFoundException {
        StartAppAction action = new StartAppAction(appPackage);
        action.perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }

    public Node buildNode(UiDevice device) throws UiObjectNotFoundException {
        Node node = new Node();
        createActions(node, device);
        buildVertex(node, device);
        return node;
    }

    public void createActions(Node node, UiDevice device) throws UiObjectNotFoundException {
        node.getAvailableActions().addAll(ActionFactory.createInputActions(node, device));
        node.getAvailableActions().addAll(ActionFactory.createButtonActions(node, device));

        node.getAvailableActions().addAll(ActionFactory.createRadioActions(node, device));
        node.getAvailableActions().addAll(ActionFactory.createcheckBoxActions(node, device));
        node.getAvailableActions().addAll(ActionFactory.createSelectsActions(node, device));
        node.getAvailableActions().addAll(ActionFactory.createDatesActions(node, device));

    }


    private List<UiObject> findInputTexts(UiDevice device) throws UiObjectNotFoundException {
        List<UiObject> result = new ArrayList<>();
        UiSelector selector = new UiSelector().className(EditText.class);
        UiCollection collection = new UiCollection(selector);
        for (int i = 0; i < collection.getChildCount(selector); i++)
            result.add(collection.getChildByInstance(selector, i));
        return result;
    }


    public List<UiObject> findButtons(UiDevice device) throws UiObjectNotFoundException {
        List<UiObject> result = new ArrayList<>();
        BySelector sel = By.clazz("android.widget.Button");
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


    public void buildVertex(Node node, UiDevice device) throws UiObjectNotFoundException {
        for (Action a : node.getAvailableActions()) {
            try {
                a.perform();
                if (!isSameNode(node, device)) {
                    Node nextNode = buildNode(device);
                    node.getOutputVertex().put(a, nextNode);
                    Action goBack = new GoBackAction(device);
                    nextNode.getOutputVertex().put(goBack, node);
                    goBack.perform();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Clearly, this implementation is simplistic and can lead to inconsistent behaviour when applied
     * to complex user interfaces, but for a first approach it could be a starting point.
     *
     * @param currentNode
     * @param device
     * @return Whether we are in a new UI state or not.
     * @throws UiObjectNotFoundException
     */

    public boolean isSameNode(Node currentNode, UiDevice device) throws UiObjectNotFoundException {
        boolean result = true;
        List<Action> inputTexts = ActionFactory.createInputActions(currentNode, device);
        List<Action> buttons = ActionFactory.createButtonActions(currentNode, device);
        for (int i = 0; i < inputTexts.size() && result; i++)
            result = (result && currentNode.getAvailableActions().contains(inputTexts.get(i)));
        for (int i = 0; i < buttons.size() && result; i++)
            result = (result && currentNode.getAvailableActions().contains(buttons.get(i)));
        result = (result && currentNode.getAvailableActions().size() == (inputTexts.size() + buttons.size()));
        return result;
    }

}
