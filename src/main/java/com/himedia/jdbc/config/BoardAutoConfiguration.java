package com.himedia.jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.himedia.jdbc.util.JDBCConnectionManager;

//JDBCConnectionManager를 빈으로 등록하는 환경 설정 클래스
@Configuration//@Configuration을 가지고 있으므로 자동으로 빈으로 등록되며, 동시에 BoardAutoConfiguration이 가지고 있는 @Bean 설정들도 모두 처리되므로 JDBCConnectionManager 객체도 빈으로 등록됨
@EnableConfigurationProperties(JDBCConnectionManagerProperties.class)	//활성화 할 프로퍼티 클래스를 지정할 때 사용. 그리고 활성화 된 프로퍼티 객체(JDBCConnectionManagerProperties)는 @AutoWired로 의존성 주입하여 사용할 수 있다.
public class BoardAutoConfiguration {
	
	@Autowired
	private	JDBCConnectionManagerProperties properties;
	
	@Bean
	@ConditionalOnMissingBean	//등록하려는 빈이 메모리에 없는 경우에만 현재의 빈 등록을 처리한다. 지금은 우리가 h2를 사용할 것이기 때문에 Chapter2에 JDBCConnectionManager객체가 있으므로 현재 이 빈은 생성되지 않음
	public JDBCConnectionManager getJDBCConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setDriverClass(properties.getDriverClass());
		manager.setUrl(properties.getUrl());
		manager.setUsername(properties.getUsername());
		manager.setPassword(properties.getPassword());
		return manager;
	}
}
