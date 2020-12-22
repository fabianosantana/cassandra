package com.santander.cassandra.conf;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.nosan.embedded.cassandra.Version;
import com.github.nosan.embedded.cassandra.cql.CqlScript;
import com.github.nosan.embedded.cassandra.local.LocalCassandraFactory;
import com.github.nosan.embedded.cassandra.test.CqlSessionConnectionFactory;
import com.github.nosan.embedded.cassandra.test.CqlSessionFactory;
import com.github.nosan.embedded.cassandra.test.junit5.CassandraExtension;
import com.github.nosan.embedded.cassandra.test.spring.EmbeddedCassandra;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.nio.file.Paths;
@EmbeddedCassandra
public class SessionCuston {

    private static final String DIRECTORY = "C:/Users/fona/";
    //private static final String DIRECTORY = "http://artifactory.santanderbr.corp/artifactory/webapp/#/artifacts/browse/tree/General/raw-downloads/cassandra/3.11.9/\n";
    private static final String HOME= "C:/Users/fona/cassandra_home";
    private static final String VERSION = "3.11.9";
    private static final String SCRIPT = "schema.cql";

    /**
     * Configured Cassandra Factory.
     */
    @RegisterExtension
    public static final com.github.nosan.embedded.cassandra.test.junit5.CassandraExtension cassandra = new CassandraExtension(() -> {
        LocalCassandraFactory factory = new LocalCassandraFactory();
        factory.setWorkingDirectory(Paths.get(HOME));
        factory.setArtifactDirectory(Paths.get(DIRECTORY));
        Version versionTest = Version.parse(VERSION);
        factory.setVersion(versionTest);
        return factory.create();
    }, new CqlSessionConnectionFactory(), CqlScript.classpath(SCRIPT));

    public static CqlSession session;

    @BeforeAll
    static void initSession() {
        session = new CqlSessionFactory().create(cassandra.getSettings());
    }

    @AfterAll
    static void closeSession() {
        if (session != null) {
            session.close();
        }
    }
}