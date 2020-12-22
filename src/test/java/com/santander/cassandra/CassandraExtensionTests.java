package com.santander.cassandra;

import org.junit.jupiter.api.Test;
import com.santander.cassandra.conf.SessionCuston;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CassandraExtensionTests extends SessionCuston{

    @Test
    void findAll() {
        assertThat(session.execute("SELECT * FROM  test.clients")).hasSize(2);
    }

}