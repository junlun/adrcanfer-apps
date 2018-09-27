package es.us.isa.androidtesting;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import es.us.isa.androidtesting.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import es.us.isa.androidtesting.inagraph.INAGraph;
import es.us.isa.androidtesting.inagraph.INAGraphBuilder;
import es.us.isa.androidtesting.algorithms.RandomSearch;
import es.us.isa.androidtesting.objectivefunctions.ApplicationCrashObjectiveFunction;
import es.us.isa.androidtesting.objectivefunctions.ObjectiveFunction;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by japar on 17/03/2018.
 */

public class RandomSearchForAbruptShutdown {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void test1() throws UiObjectNotFoundException, InterruptedException {
        Log.i("INFO: ","HOLIWIIIIIIIIIIIIIIIIIIIIIII");
        UiDevice mDevice;
        mDevice = UiDevice.getInstance(getInstrumentation());
        thrown.expect(Throwable.class);
        INAGraph graph= INAGraphBuilder.getInstance().build(mDevice,"com.example.testingandroid2"); //Construir el grafo de acciones
        ObjectiveFunction abruptShutdown=new ApplicationCrashObjectiveFunction(); //Función de crash. Realiza el test, si captura excepción devuelve si no devuelve
        RandomSearch algorithm=new RandomSearch(abruptShutdown,100,3);
        TestCase testCase=algorithm.run(graph,"com.example.testingandroid2");
        Log.i("INFO: ","Test case found: "+testCase);

    }
}
