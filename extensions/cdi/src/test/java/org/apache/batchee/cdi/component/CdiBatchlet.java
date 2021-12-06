/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.batchee.cdi.component;

import javax.batch.api.AbstractBatchlet;
import javax.batch.api.BatchProperty;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Named;

import mypkg.AABean;
import mypkg.BatchPropertyLiteral;

@Named
@Dependent
public class CdiBatchlet extends AbstractBatchlet {
    @Inject
    private JobScopedBean jobScopedBean;

    @Inject
    private StepScopedBean stepScopedBean;

    @Inject
    @BatchProperty(name = "aa")
    String aa;

    /*
     *  Logs will show this bean can get batch props & context injected
     */
    @Inject
    AABean aaBean;

    @Inject
    StepContext stepCtx;
    
    @Override
    public String process() throws Exception {
		System.out.println("SKSK: In process, aa = " + aa);
        aaBean.m1();
        System.out.println("process" + "step name = " + stepCtx.getStepName());
		Instance<String> myBatchProp = CDI.current().select(String.class, new BatchPropertyLiteral("aa"));
        /*
         * returns 'null' in BatchEE since OWB passes 'null' InjectionPoint to producer.  In JBeret & Open Liberty 
         * w/ Weld we get a non-null InjectionPoint but hit NPE trying to parse the field name to calculate default.
         */
		String lazy = myBatchProp.get();
		System.out.println("SKSK: In process, lazy = " + lazy);

        Holder.JOB_SCOPED_IDS.add(jobScopedBean.getId());
        Holder.STEP_SCOPED_IDS.add(stepScopedBean.getId());
        return Long.toString(stepScopedBean.getId());
    }
}
