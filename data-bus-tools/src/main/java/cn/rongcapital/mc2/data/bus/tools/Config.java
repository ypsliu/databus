package cn.rongcapital.mc2.data.bus.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

	@Value("${spring.data.neo4j.uri}")
	private String uri;

	@Primary
	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
		config.driverConfiguration()
			  .setConnectionPoolSize(100)
			  .setURI(uri);
		return config;
	}

	/*@Primary
	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), "cn.rongcapital.mc2.data.bus.tools.mock.common.domain");
	}

	@Primary
	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}*/

}
