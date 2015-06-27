/*
 * The MIT License
 *
 * Copyright 2015.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.workflow;

import hudson.slaves.DumbSlave;

import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * Verifies that specific environment variables are available.
 *
 */
public class EnvWorkflowTest {

    @Rule public JenkinsRule r = new JenkinsRule();

    @Test public void areAvailable() throws Exception {
        WorkflowJob p = r.jenkins.createProject(WorkflowJob.class, "workflow-test");
        DumbSlave s = r.createSlave("node-test", null, null);
        s.getComputer().connect(false).get();

        p.setDefinition(new CpsFlowDefinition("node('node-test') { echo \"My name is ${env.NODE_NAME}\" }"));
        r.assertLogContains("My name is node-test", r.assertBuildStatusSuccess(p.scheduleBuild2(0)));
    }

}