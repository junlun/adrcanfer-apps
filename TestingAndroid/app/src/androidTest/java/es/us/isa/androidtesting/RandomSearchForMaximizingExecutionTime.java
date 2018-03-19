package es.us.isa.androidtesting;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;

import es.us.isa.androidtesting.TestCase;
import es.us.isa.androidtesting.algorithms.RandomSearch;
import es.us.isa.androidtesting.inagraph.INAGraph;
import es.us.isa.androidtesting.inagraph.INAGraphBuilder;
import es.us.isa.androidtesting.objectivefunctions.ObjectiveFunction;
import es.us.isa.androidtesting.objectivefunctions.TestExecutionTimeObjectiveFunction;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class RandomSearchForMaximizingExecutionTime {

    @Test
    public void test1() throws UiObjectNotFoundException, InterruptedException {
        UiDevice mDevice;
        mDevice = UiDevice.getInstance(getInstrumentation());
        INAGraph graph = INAGraphBuilder.getInstance().build(mDevice,"TestingAndroid");
        ObjectiveFunction abruptShutdown = new TestExecutionTimeObjectiveFunction();
        RandomSearch algorithm = new RandomSearch(abruptShutdown, 20, 2);
        TestCase testCase = algorithm.run(graph, "com.example.testingandroid");
        System.out.println("Test case found: " + testCase);
        System.out.println("Runnig it...");
        testCase.executeBefore();
        testCase.executeTest();
        testCase.executeAfter();
        System.out.println("Done!");
    }
}
