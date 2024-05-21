package edu.wgu.d387_sample_code.service;

import jdk.jshell.SourceCodeAnalysis;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Service
public class Translation {
    private final ExecutorService messageExecutor=newFixedThreadPool(2);
    public String [] getWelcomeMessages() throws Exception {
        Callable<String> task1 = () -> loadMessage("translation_en_US.properties");
        Callable<String> task2 = () -> loadMessage("translation_fr_CA.properties");

        Future<String> future1 = messageExecutor.submit(task1);
        Future<String> future2 = messageExecutor.submit(task2);

        String message1 = future1.get();
        String message2 = future2.get();

        return new String[]{message1, message2};
    }

    private String loadMessage(String resourceName) {
        Properties properties = new Properties();
        try (InputStream stream = new ClassPathResource(resourceName).getInputStream()) {
            properties.load(stream);
            return properties.getProperty("welcome");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}