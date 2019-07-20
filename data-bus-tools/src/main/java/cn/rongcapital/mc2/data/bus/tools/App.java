package cn.rongcapital.mc2.data.bus.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.rongcapital.graeae.endpoint.autoconfigure.annotation.EnableGraeae;
import cn.rongcapital.mc2.data.bus.GraeaeDataBusClientConfig;
import cn.rongcapital.mc2.data.common.camel.CamelConfig;
import cn.rongcapital.mc2.data.common.validation.ValidationConfig;

/**
 * @author WUYB
 */
@EnableGraeae
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableNeo4jRepositories("cn.rongcapital.mc2.data.bus.tools.mock.common.repository")
@EntityScan(basePackages = "cn.rongcapital.mc2.data.bus.tools.mock.common.domain")
@PropertySource({ "classpath:application-mock.properties", "classpath:application-graeae.properties"})
@Import({ GraeaeDataBusClientConfig.class, ValidationConfig.class, CamelConfig.class })
@SpringBootApplication(scanBasePackages = { "cn.rongcapital.mc2.data.bus.tools", "cn.rongcapital.mc2.data.common.utils"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
