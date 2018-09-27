package es.us.isa.androidtesting.inagraph;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.uiautomator.UiSelector.*;


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
        node.getAvailableActions().addAll(createInputActions(node, device));
        node.getAvailableActions().addAll(createButtonActions(node, device));

    }

    public List<Action> createInputActions(Node node, UiDevice device) throws UiObjectNotFoundException {
        TextInputGenerator generator = new TextInputGenerator();
//        List<UiObject> inputTexts=findInputTexts(device);
        List<UiObject> inputTexts = findElements(device, "android.widget.EditText");
        List<Action> result = new ArrayList<>();
        for (UiObject input : inputTexts) {
            result.add(new InputAction(input, generator));
            node.getControls().add(input);
        }
        return result;
    }

    private List<UiObject> findInputTexts(UiDevice device) throws UiObjectNotFoundException {
        List<UiObject> result = new ArrayList<>();
        UiSelector selector = new UiSelector().className(EditText.class);
        UiCollection collection = new UiCollection(selector);
        for (int i = 0; i < collection.getChildCount(selector); i++)
            result.add(collection.getChildByInstance(selector, i));
        return result;
    }

    public List<Action> createButtonActions(Node node, UiDevice device) throws UiObjectNotFoundException {
        List<Action> result = new ArrayList<>();
//        List<UiObject> buttons=findButtons(device);
        List<UiObject> buttons = findElements(device, "android.widget.Button");
        for (UiObject input : buttons) {
            result.add(new ButtonAction(input));
            node.getControls().add(input);
        }
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

    public List<UiObject> findElements(UiDevice device, String finder) {
        List<UiObject> result = new ArrayList<>();
        BySelector sel = resolveSelector(finder);
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

    public BySelector resolveSelector(String finder) {
        BySelector result = null;
        finder = finder.substring(finder.lastIndexOf(".") + 1);
        if (finder.equalsIgnoreCase("button")) {
            result = By.clazz(Button.class);
        } else if (finder.equalsIgnoreCase("EditText")) {
            result = By.clazz(EditText.class);
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
        try {
            List<Action> inputTexts = createInputActions(currentNode, device);
            List<Action> buttons = createButtonActions(currentNode, device);
            for (int i = 0; i < inputTexts.size() && result; i++)
                result = (result && currentNode.getAvailableActions().contains(inputTexts.get(i)));
            for (int i = 0; i < buttons.size() && result; i++)
                result = (result && currentNode.getAvailableActions().contains(buttons.get(i)));
            result = (result && currentNode.getAvailableActions().size() == (inputTexts.size() + buttons.size()));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
