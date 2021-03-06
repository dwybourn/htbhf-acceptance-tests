package uk.gov.dhsc.htbhf.browserstack;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Generates an HTML file from the provided test results
 */
public class TestOutputHtmlGenerator {

    private static final String VELOCITY_TEMPLATE_LOCATION = "velocity/results-summary.vm";

    public static void generateHtmlReport(List<TestResultSummary> results, String reportLocation) throws IOException {
        List<TestResultSummary> resultSummaries = new ArrayList<>(results);
        resultSummaries.sort(Comparator.naturalOrder());
        VelocityEngine velocityEngine = setupVelocityEngine();

        Template velocityEngineTemplate = velocityEngine.getTemplate(VELOCITY_TEMPLATE_LOCATION);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("resultSummaries", resultSummaries);

        StringWriter writer = new StringWriter();
        velocityEngineTemplate.merge(velocityContext, writer);
        Files.writeString(Path.of(reportLocation), writer.toString());
    }

    private static VelocityEngine setupVelocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        return velocityEngine;
    }
}
