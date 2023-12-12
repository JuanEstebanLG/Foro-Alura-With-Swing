package com.alura.foro.foro.me;

import com.alura.foro.foro.me.swingComponents.Login.LoginFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@EnableJpaRepositories
public class AppForoApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AppForoApplication.class);
		builder.headless(false).run(args);
		SwingUIApplication.iniciarAppSwing();
	}



}
