package org.camunda.bpm.bdd;

import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;

import java.net.URL;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.needle.NeedleAnnotatedEmbedderRunner;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.steps.PrintStreamStepMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(NeedleAnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true, verboseFailures = true)
@Configure(stepMonitor = PrintStreamStepMonitor.class, pendingStepStrategy = FailingUponPendingStep.class, stepdocReporter = PrintStreamStepdocReporter.class, storyReporterBuilder = RichReporterBuilder.class)
public abstract class InjectableTestBase extends InjectableEmbedder {


  /**
   * Retrieves the location of the stories. <br>
   * This method is intended to be overwritten on divergent location for stories
   * than src/test/resources
   * 
   * @return location of the stories to look for.
   */
  protected URL getStoryLocation() {
    return codeLocationFromPath(JBehaveConstants.DEFAULT_STORY_LOCATION);
  }

  /**
   * Retrieves the location of the stories.
   */
  protected List<String> storyPaths() {
    return new StoryFinder().findPaths(getStoryLocation(), JBehaveConstants.STORY_PATTERN, JBehaveConstants.NO_EXCLUDE);
  }

  @Override
  @Test
  public void run() {
    injectedEmbedder().runStoriesAsPaths(storyPaths());
  }

}