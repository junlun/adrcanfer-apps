package es.us.isa.androidtesting.algorithms;

import android.support.test.uiautomator.UiObjectNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import es.us.isa.androidtesting.TestCase;
import es.us.isa.androidtesting.inagraph.CloseAppAction;
import es.us.isa.androidtesting.inagraph.INAGraph;
import es.us.isa.androidtesting.inagraph.Action;
import es.us.isa.androidtesting.inagraph.StartAppAction;
import es.us.isa.androidtesting.objectivefunctions.ObjectiveFunction;

public class RandomSearch {

    ObjectiveFunction objective;
    long iterations;
    long actionsLength;

    public RandomSearch(ObjectiveFunction objective, long iterations, int actionsLength) {
        this.objective = objective;
        this.iterations = iterations;
        this.actionsLength=actionsLength;
    }

    public TestCase run(INAGraph graph,String app) throws UiObjectNotFoundException {
        TestCase candidate=buildRandomTestCase(graph,app);
        TestCase result=candidate;
        double eval=objective.evaluate(result);
        double currentBestEval=eval;
        int i=1;
        while(i<iterations){

            candidate=buildRandomTestCase(graph,app);
            eval=objective.evaluate(candidate);
            if(eval>currentBestEval){
                currentBestEval=eval;
                result=candidate;
            }
            i++;
        }
        return result;
    }

    private TestCase buildRandomTestCase(INAGraph graph,String app) throws UiObjectNotFoundException {
        graph.reset();
        List<Action> beforeActions=new ArrayList<>();
        beforeActions.add(new StartAppAction(app));
        List<Action> afterActions=new ArrayList<>();
        afterActions.add(new CloseAppAction(app));
        List<Action> testActions=new ArrayList<>();
        List<Action> candidateActions=null;
        Action chosenAction=null;
        while(testActions.size()<actionsLength){
            candidateActions=graph.getAvailableActions();
            chosenAction=candidateActions.get((int)(Math.random()*candidateActions.size()));
            testActions.add(chosenAction);
            chosenAction.perform();
        }
        return new TestCase(app, Collections.EMPTY_SET,beforeActions,testActions,afterActions);
    }
}
