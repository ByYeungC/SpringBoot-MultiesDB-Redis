package com.byyeungc.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.byyeungc.config.DBConfig1;
import com.mysql.cj.jdbc.MysqlXADataSource;

/**
 * 读取DataSource01数据源<br>
 */
// DataSource01
@Configuration
@MapperScan(basePackages = "com.byyeungc.test01", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSource1Config {
		//use Atomikos配置数据源
		@Bean(name = "testDataSource")
		public DataSource testDataSource(DBConfig1 testConfig) throws SQLException {
			MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
			mysqlXaDataSource.setUrl(testConfig.getUrl());
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
			mysqlXaDataSource.setPassword(testConfig.getPassword());
			mysqlXaDataSource.setUser(testConfig.getUsername());
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
			//将本地事务注册到A 到创建 Atomios全局事务
			AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
			xaDataSource.setXaDataSource(mysqlXaDataSource);
			xaDataSource.setUniqueResourceName("testDataSource");
			
			xaDataSource.setMinPoolSize(testConfig.getMinPoolSize());
			xaDataSource.setMaxPoolSize(testConfig.getMaxPoolSize());
			xaDataSource.setMaxLifetime(testConfig.getMaxLifetime());
			xaDataSource.setBorrowConnectionTimeout(testConfig.getBorrowConnectionTimeout());
			xaDataSource.setLoginTimeout(testConfig.getLoginTimeout());
			xaDataSource.setMaintenanceInterval(testConfig.getMaintenanceInterval());
			xaDataSource.setMaxIdleTime(testConfig.getMaxIdleTime());
			xaDataSource.setTestQuery(testConfig.getTestQuery());
			return xaDataSource;
		}

		@Bean(name = "test1SqlSessionFactory")
		public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource") DataSource dataSource)
				throws Exception {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			return bean.getObject();
		}

		@Bean(name = "test1SqlSessionTemplate")
		public SqlSessionTemplate testSqlSessionTemplate(
				@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
		
		//no use Atomikos配置数据源
//      @Bean(name = "test1DataSource")
//   	@ConfigurationProperties(prefix = "spring.datasource.test1")
//   	public DataSource testDataSource() {
//   		return DataSourceBuilder.create().build();
//   	}
//   	@Bean(name = "test1SqlSessionFactory")
//   	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
//   			throws Exception {
//   		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//   		bean.setDataSource(dataSource);
//   		// bean.setMapperLocations(
//   		// new
//   		// PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
//   		return bean.getObject();
//   	}
//
//   	@Bean(name = "test1TransactionManager")
//   	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
//   		return new DataSourceTransactionManager(dataSource);
//   	}
//
//   	@Bean(name = "test1SqlSessionTemplate")
//   	public SqlSessionTemplate testSqlSessionTemplate(
//   			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//   		return new SqlSessionTemplate(sqlSessionFactory);
//   	}
}
