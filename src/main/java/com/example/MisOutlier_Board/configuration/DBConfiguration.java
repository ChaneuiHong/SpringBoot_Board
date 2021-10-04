package com.example.MisOutlier_Board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


// 스프링 IoC Container에게 해당 파일이 환경 설정과 관련된 파일(Bean 구성 Class)이라는 것을 인식
@Configuration
// 스프링 IoC Container에서 런타임시 properties값을 가져오기 위함
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	// Bean 객체를 관리하기 위함
	@Autowired
	private ApplicationContext applicationContext;
	
	
	//Hikari는 커넥션 풀(Connection Pool) 중에 하나로, DB 접근을 효율적으로 하기 위한 객체
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	// DataSource는 커넥션 풀을 지원하기 위한 인터페이스
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	
	// SqlSessionFactory은 DB커넥션과 SQL 실행을 위한 중요한 역할을 수행함.
	// 마이바티스 XML Mapper, 설정 파일 위치 등을 지정
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		//XML Mapper를 인식하기 위한 설정
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		// alias 설정 com.example..domain.BoardDTO -> resultType"Board" 
		factoryBean.setTypeAliasesPackage("com.example.MisOutlier_Board.domain");
		factoryBean.setConfiguration(mybatisConfg());
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfg() {
		return new org.apache.ibatis.session.Configuration();
	}
	
}