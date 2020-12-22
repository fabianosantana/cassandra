package com.santander.cassandra.conf;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.nosan.embedded.cassandra.Cassandra;
import com.github.nosan.embedded.cassandra.Settings;
import com.github.nosan.embedded.cassandra.Version;
import com.github.nosan.embedded.cassandra.local.LocalCassandraFactory;
import com.github.nosan.embedded.cassandra.test.CqlSessionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class CqlSessionCuston{

    private static final String DIRECTORY = "C:/Users/fona/";
    private static final String HOME= "C:/Users/fona/cassandra_home";
    private static final String VERSION = "3.11.9";

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CqlSession cqlBean(){
        return getSession();
    }

    public static CqlSession getSession() {
        Cassandra cassandra = startLocal(Paths.get(DIRECTORY), Paths.get(HOME));
        Settings settings = cassandra.getSettings();
        return new CqlSessionFactory().create(settings);
    }

    private static Cassandra startLocal(Path rootArtifactDirectory, Path workDirectory){
        LocalCassandraFactory cassandraFactory = new LocalCassandraFactory();
        cassandraFactory.setWorkingDirectory(workDirectory);
        cassandraFactory.setArtifactDirectory(rootArtifactDirectory);
        Version versionTest = Version.parse(VERSION);
        cassandraFactory.setVersion(versionTest);
        Cassandra cassandra = cassandraFactory.create();
        cassandra.start();
        return cassandra;
    }
}
