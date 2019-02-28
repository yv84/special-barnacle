package me.yv84.specialbarnacle.studiousspoon;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = StudiousSpoonApplicationTests.TestingDataSourceConfig.class,
		loader = org.springframework.test.context.support.AnnotationConfigContextLoader.class)

public class StudiousSpoonApplicationTests {

	@Configuration
	public static class TestingDataSourceConfig {

		@Bean
		@Primary
		public DataSource dataSource() {
			return mock(DataSource.class);
		}
	}

	@Test
	public void contextLoads() {
	}

}
