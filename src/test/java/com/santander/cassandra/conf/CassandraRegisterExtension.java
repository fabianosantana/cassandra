package com.santander.cassandra.conf;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.nosan.embedded.cassandra.Version;
import com.github.nosan.embedded.cassandra.cql.CqlScript;
import com.github.nosan.embedded.cassandra.local.LocalCassandraFactory;
import com.github.nosan.embedded.cassandra.test.CqlSessionConnectionFactory;
import com.github.nosan.embedded.cassandra.test.CqlSessionFactory;
import com.github.nosan.embedded.cassandra.test.junit5.CassandraExtension;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class CassandraRegisterExtension implements BeforeAllCallback {

    private static final String DIRECTORY = "C:/Users/fona/";
    private static final String HOME= "C:/Users/fona/cassandra_home";
    private static final String VERSION = "3.11.9";

    CqlSession session;
    CassandraExtension cassandra;


    Logger logger = LoggerFactory.getLogger(CassandraRegisterExtension.class);
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        logger.info("método beforeAll");
        cassandra =  new CassandraExtension(() -> {
            LocalCassandraFactory factory = new LocalCassandraFactory();
            factory.setWorkingDirectory(Paths.get(HOME));
            factory.setArtifactDirectory(Paths.get(DIRECTORY));
            Version versionTest = Version.parse(VERSION);
            factory.setVersion(versionTest);
            return factory.create();
        }, new CqlSessionConnectionFactory(), CqlScript.classpath("schema.cql"));
    }

    public CqlSession getSession() {
        this.session = new CqlSessionFactory().create(cassandra.getSettings());
        return this.session;
    }

    public CassandraExtension getCassandra() {
        return this.cassandra;
    }

}
