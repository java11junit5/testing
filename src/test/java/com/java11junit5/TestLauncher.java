package com.java11junit5;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class TestLauncher {
    private SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public static final Logger LOGGER = LoggerFactory.getLogger(TestLauncher.class);

    public static void main(String[] args) {
        TestLauncher runner = new TestLauncher();
        runner.runTests();

        LOGGER.info("ВЫПОЛНЕНИЕ АВТОТЕСТОВ ЗАВЕРШЕНО");

        TestExecutionSummary summary = runner.listener.getSummary();
        summary.printTo(new PrintWriter(System.out));
    }


    private void runTests() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(selectPackage("com.java11junit5.suites"))
                .build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        LOGGER.info("АВТОТЕСТЫ ЗАПУЩЕНЫ");
    }
}
