package app.test;

import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

public class TestRunner {

    @Test
    void testSuite() {

        String tag = System.getProperty("karate.tag");

        if (tag == null || tag.isBlank()) {
            Runner.builder()
                    .path("classpath:features")
                    .reportDir("target/site/karate-reports")
                    .parallel(1);
        } else {
            Runner.builder()
                    .path("classpath:features")
                    .tags(tag)
                    .reportDir("target/site/karate-reports")
                    .parallel(1);
        }
    }
}