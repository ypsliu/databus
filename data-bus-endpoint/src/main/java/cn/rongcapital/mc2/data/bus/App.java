package cn.rongcapital.mc2.data.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import cn.rongcapital.graeae.endpoint.autoconfigure.annotation.EnableGraeae;

/**
 * @author WUYB
 */
@SpringBootApplication
@EnableGraeae
@PropertySource(value = { "classpath:application-graeae.properties" })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
