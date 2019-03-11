package me.yv84.specialbarnacle.fuzzypancake.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.config.SchemaAction;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;


@Configuration
//@PropertySource(value = { "classpath:cassandra.yml" })
//@ConfigurationProperties("spring.data.cassandra")
//@EnableCassandraRepositories(basePackages = "me.yv84.specialbarnacle.fuzzypancake.persistance.repository")
@EnableReactiveCassandraRepositories(basePackageClasses = {CassandraConfig.class})
//@EnableCassandraRepositories(basePackageClasses = {CassandraConfig.class})
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspace-name}")
	protected String keyspaceName;

	@Value("${spring.data.cassandra.contactpoints}")
	protected String contactpoints;


	@Value("${spring.data.cassandra.port}")
	protected int port;


	@Override
	protected String getKeyspaceName() {
		return this.keyspaceName;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected List getKeyspaceCreations() {
		return Collections.singletonList(CreateKeyspaceSpecification
				.createKeyspace(keyspaceName).ifNotExists()
				.with(KeyspaceOption.DURABLE_WRITES, true)
				.withSimpleReplication());
	}

	@Override
	protected boolean getMetricsEnabled() { return false; }

	@Override
	public String getContactPoints() {
		return contactpoints;
	}

	@Override
	protected int getPort() {
		return port;
	}


}
