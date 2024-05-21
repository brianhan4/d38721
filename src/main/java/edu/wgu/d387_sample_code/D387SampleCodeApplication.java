package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;


import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

@SpringBootApplication
public class D387SampleCodeApplication {
	static ExecutorService messageExecutor=newFixedThreadPool(2);
	public static void main(String[] args) {

		SpringApplication.run(D387SampleCodeApplication.class, args);
		Properties properties = new Properties();
		Callable<String> task1 = () -> {
			try {
				InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream();
				properties.load(stream);
				return properties.getProperty("welcome");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		};

		//try {
		//    Future<String> future = messageExecutor.submit(task);
		//    String welcomeMessage = future.get();
		//    System.out.println(welcomeMessage);
		//} catch (Exception e) {
		//    e.printStackTrace();
		//}
		Callable<String> task2 = () -> {
			try {
				InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream();
				properties.load(stream);
				return properties.getProperty("welcome");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		};
		messageExecutor.shutdown();

	}

}

