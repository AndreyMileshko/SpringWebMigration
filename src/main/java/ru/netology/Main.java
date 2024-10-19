package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, LifecycleException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory("tomcat");
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(9998);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.netology");

        String contextPath = "";
        String servletName = "dispatcher";
        final var dispatcherServlet = new DispatcherServlet(context);
        final var registration = tomcat.addServlet(contextPath, servletName, dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

        tomcat.start();
        tomcat.getServer().await();
    }
}