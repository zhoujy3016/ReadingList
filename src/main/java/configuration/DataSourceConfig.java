package configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	@Bean(name="dataSource")
	public DataSource datasource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDataSourceClassName(env.getProperty("spring.datasource.driverClassName"));
		return ds;
	}
}
