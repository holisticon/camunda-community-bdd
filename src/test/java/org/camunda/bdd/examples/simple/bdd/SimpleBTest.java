package org.camunda.bdd.examples.simple.bdd;

import org.camunda.bdd.examples.simple.steps.SimpleProcessSteps;
import org.camunda.bpm.bdd.JUnitTestBase;
import org.camunda.bpm.bdd.steps.CamundaSteps;

/**
 * JBehave tests for the simple process using configurable embedder.
 */
public class SimpleBTest extends JUnitTestBase {

  @Override
  public Class<?>[] getStepClasses() {
    return new Class<?>[] { SimpleProcessSteps.class, CamundaSteps.class };
  }

}
