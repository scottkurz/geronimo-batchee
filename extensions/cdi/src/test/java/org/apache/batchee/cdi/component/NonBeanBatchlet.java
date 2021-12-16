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
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;


public class NonBeanBatchlet extends AbstractBatchlet {

    @Inject
    @BatchProperty(name = "f1")
    String f1;

    @Inject
    @BatchProperty
    String f2;
    
    String c1;
    String c2;

    String m1;
    String m2;

    String s1;

	public NonBeanBatchlet() {
	}


	@Inject
	public NonBeanBatchlet(@BatchProperty(name="c1") String c1) {
		this.c1 = c1;
	}

	/*
	@Inject
	NonBeanBatchlet(@BatchProperty(name="c1") String c1, @BatchProperty String c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	*/

	@Inject  
	public void setMethod1(@BatchProperty(name="m1") String m1) {
	 	this.m1 = m1;
	}
    
	@Inject  
	public void setMethod2(@BatchProperty String m2) {
	 	this.m2 = m2;
	}


	@Inject  
	@BatchProperty(name="s1")
	public void setterMethod1(String s1) {
	 	this.s1 = s1;
	}
 
    @Override
    public String process() throws Exception {
    	
		System.out.println("SKSK: In process, f1 = " + f1);
		System.out.println("SKSK: In process, f2 = " + f2);
		System.out.println("SKSK: In process, m1 = " + m1);
		System.out.println("SKSK: In process, m2 = " + m2);
		System.out.println("SKSK: In process, c1 = " + c1);
		System.out.println("SKSK: In process, c2 = " + c2);
		System.out.println("SKSK: In process, s1 = " + s1);

		return "OK";

    }
}
