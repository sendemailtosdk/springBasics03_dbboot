package com.springBasics.configuration;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//import org.eclipse.persistence.internal.jpa.EntityManagerFactoryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.springBasics.entities.Vendor;
import com.zaxxer.hikari.HikariDataSource;

//@Profile({"local","cf-test","cf-dev","cf-prod"})
@Configuration
//Profile("cloud"
public class DatabaseConfig extends AbstractCloudConfig {
	/*
	 * (Step 1) Parses the local environment variables VCAP_SERVICES(containing
	 * cloud information) and provides a DataSource. The superclass {@link
	 * AbstractCloudConfig}, part of the Spring Cloud plugin, is used for this.
	 * 
	 */

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${vcap.services.HanaSchema.credentials.user}")
	private String username;

	@Value("${vcap.services.HanaSchema.credentials.password}")
	private String password;

	@Value("${vcap.services.HanaSchema.credentials.url}")
	private String hostname;

	@Value("${vcap.services.HanaSchema.credentials.port}")
	private String port;

	@Value("${vcap.services.HanaSchema.credentials.schema}")
	private String schema;

	@Bean
	public DataSource dataSource() {
		/*
		 * Load BasicDbcpPooledDataSourceCreator before
		 * TomcatJdbcPooledDataSourceCreator. Also see the following link for a
		 * detailed discussion of this issue:
		 * https://stackoverflow.com/questions/36885891/jpa-eclipselink-understanding-classloader-issues
		 * */
		List<String> dataSourceNames = Arrays.asList("BasicDbcpPooledDataSourceCreator",
				"TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator",
				"TomcatDbcpPooledDataSourceCreator");

		DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
	//	DataSource hikariDataSource = connectionFactory().dataSource(dbConfig);
		DataSource hanaDataSource =  DataSourceBuilder.create()
				                            .type(HikariDataSource.class)
				                            .driverClassName(com.sap.db.jdbc.Driver.class.getName())
				                            .url(hostname)
				                            .username(username)
				                            .password(password)
				                            .build();

		logger.info("Detected Host Name is : " + this.hostname);
		logger.info("Detected Port Name is : " + this.port);
		logger.info("Detected schema Name is : " + this.schema);
		logger.info("Detected User Name is : " + this.username);
		logger.info("Detected User Password is : " + this.password);
		return hanaDataSource;

	}

	/*
	 * (Step 2a ) Based on a {@link DataSource} provided using the method above ) ,
	 * provided a factory to create {@link javax.persistance.EntityManager} instance
	 * ( a core class of JPA) . Also see {@link EntityManagerFactoryProvider}.
	 */

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource myDataSource) {
		return EntityManagerFactoryProvider.get(myDataSource, Vendor.class.getPackage().getName());

	}
/*
 * (step 2b ) Based on an {@link EntityManagerFactory} provided using the method
 * above), provides a {@link JpaTransactionManager} (another core class of JPA)
 * */
	@Bean(name = "transactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManager) {
		return new JpaTransactionManager(entityManager);
	}



}