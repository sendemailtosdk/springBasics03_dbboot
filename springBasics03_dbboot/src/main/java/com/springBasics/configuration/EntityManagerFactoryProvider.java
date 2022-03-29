package com.springBasics.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.jpa.PersistenceProvider;
import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.eclipse.persistence.config.PersistenceUnitProperties;
public class EntityManagerFactoryProvider {



public static LocalContainerEntityManagerFactoryBean get(DataSource dataSource,String myPackage) {
	
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean( );
	
	entityManagerFactoryBean.setPersistenceProvider(new PersistenceProvider( ));
	entityManagerFactoryBean.setPackagesToScan(myPackage);
	entityManagerFactoryBean.setDataSource(dataSource);
	
	entityManagerFactoryBean.setJpaPropertyMap(getJPAProperties(dataSource.getClass().getClassLoader()));
	entityManagerFactoryBean.setLoadTimeWeaver(new SimpleLoadTimeWeaver());
	entityManagerFactoryBean.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
	
	entityManagerFactoryBean.afterPropertiesSet();
	
	return entityManagerFactoryBean;
}	


private static Map<String,Object> getJPAProperties(ClassLoader classLoader){
	
Map<String,Object> properties = new HashMap<>();

properties.put(PersistenceUnitProperties.DDL_GENERATION,PersistenceUnitProperties.DROP_AND_CREATE);
properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE,PersistenceUnitProperties.DDL_DATABASE_GENERATION);
properties.put(PersistenceUnitProperties.CLASSLOADER,classLoader);
properties.put(PersistenceUnitProperties.LOGGING_LEVEL,"INFO");

properties.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");

properties.put(PersistenceUnitProperties.CONNECTION_POOL_MAX, 50);
return properties;
	
}
	
}
