package es.us.isa.androidtesting.objectivefunctions;

import es.us.isa.androidtesting.TestCase;

public class ApplicationCrashObjectiveFunction implements ObjectiveFunction {
    @Override
    public double evaluate(TestCase test) {
        double result=0;
        try {
            test.executeBefore();
            test.executeTest();
            test.executeAfter();
        }catch(Exception e){
            result=1;
        }
        return result;
    }
}
