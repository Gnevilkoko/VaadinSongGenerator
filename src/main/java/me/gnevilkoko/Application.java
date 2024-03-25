package me.gnevilkoko;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import com.vaadin.flow.theme.lumo.Lumo;
import me.gnevilkoko.threads.StreamThread.StreamThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "vaadinsongsgeneratorapi", variant = Lumo.DARK)
public class Application implements AppShellConfigurator {

    public static StreamThread streamThread = new StreamThread();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        startThreads();
    }

    private static void startThreads() {
        streamThread.start();
    }

    public static void sendMessage(String message){
        System.out.println(message);
    }

    public static void stopWithError(){
        System.exit(-1);
    }

}
