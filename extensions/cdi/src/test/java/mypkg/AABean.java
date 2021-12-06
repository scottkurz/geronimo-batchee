package mypkg;

import javax.batch.api.BatchProperty;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


/**
 * Show that a non-batch-artifact bean can get property + context injection based on thread local behavior
 */
@Dependent
public class AABean {
    
    @Inject
    @BatchProperty(name = "aa")
    String aa;
    
    @Inject StepContext stepCtx;
    
    public void m1() {
        System.out.println("In AABean, aa = " + aa);
        System.out.println("In AABean, step name = " + stepCtx.getStepName());
    }
}
