package com.medication.app;

import assigment3.rmi.RmiServer;
import com.medication.app.rmi.RmiServerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import java.rmi.RemoteException;

@Configuration
@ComponentScan
@EnableAutoConfiguration
//@SpringBootApplication
//@EnableZuulProxy
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean(name = "/rmiServer")
	HttpInvokerServiceExporter accountService() throws RemoteException {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(new RmiServerImpl());
		exporter.setServiceInterface( RmiServer.class );
		return exporter;
	}

}
