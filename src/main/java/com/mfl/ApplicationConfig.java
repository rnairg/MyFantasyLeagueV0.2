package com.mfl;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Configuration
public class ApplicationConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.mfl.models"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean	
	public Properties getHibernateProperties() {
		return new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3938700260142429593L;

			{
				setProperty("hibernate.hbm2ddl.auto",
			    env.getProperty("spring.jpa.hibernate.ddl-auto"));
			    setProperty("hibernate.dialect",
			    env.getProperty("spring.jpa.properties.hibernate.dialect"));
			    setProperty("hibernate.show_sql",
					    env.getProperty("spring.jpa.show-sql"));
			    setProperty("hibernate.current_session_context_class",
					    env.getProperty("current-session"));
			}
			
		};
	}

	@Bean	
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

}
