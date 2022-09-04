package com.example.templates.cassandra;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories("com.example.templates.cassandra")
public class CassandraConfig extends AbstractCassandraConfiguration  {

    @Override
    protected int getPort() {
        return 9042;
    }

    @Override
    protected String getContactPoints() {
        return "10.5.0.4";
    }

    @Override
    protected String getKeyspaceName() {
        return "rec";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
    
}
