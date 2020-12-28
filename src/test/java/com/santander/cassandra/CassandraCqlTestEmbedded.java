package com.santander.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.nosan.embedded.cassandra.test.spring.Cql;
import com.github.nosan.embedded.cassandra.test.spring.EmbeddedCassandra;
import com.santander.cassandra.conf.CqlSessionCuston;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
public class CassandraCqlTestEmbedded {

    private CqlSessionCuston sessionCuston = new CqlSessionCuston();
    private CqlSession session;

    @BeforeEach
    void startCassandra(){
        this.session = sessionCuston.cqlBean();
    }


    @Test
    @Cql(scripts = "/data_clients.cql")
    void findAllUsers() {
        assertThat(this.session.execute("SELECT * FROM test.clients")).hasSize(2);
    }
}