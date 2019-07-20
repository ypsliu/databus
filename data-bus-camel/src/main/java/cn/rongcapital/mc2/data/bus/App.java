package cn.rongcapital.mc2.data.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import cn.rongcapital.mc2.data.common.camel.CamelConfig;

/**
 * @author WUYB
 */
@SpringBootApplication
@Import({ CamelConfig.class })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
