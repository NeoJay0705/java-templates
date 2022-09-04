package com.example.templates.cassandra;

import java.util.List;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.protocol.internal.request.Query;


@Service
public class CassandraService {

    private CassandraOperations cassandraTemplate;

    public CassandraService(CassandraOperations cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public void select1(String name, int age2) {
        // Insert insert = QueryBuilder.insertInto("users")
        //     .
        Users user = new Users(name, age2);
        cassandraTemplate.insert(user);
    }

    public void select2(String name, int age2) {
        // String query = "select * from rec.users;";
        
        // this.cassandraTemplate.exe
        Delete del = QueryBuilder.deleteFrom("users").where(Relation.column("name").isEqualTo(QueryBuilder.literal(name)));
        cassandraTemplate.execute(del.build());
        // Users user = new Users(name, age2);
        // cassandraTemplate.delete(user);;
        // cassandraTemplate.delete(user);
    }

    public Object select3() {
        Select select = QueryBuilder.selectFrom("users")
            .all()
            // .where(Relation.column("name").isEqualTo(QueryBuilder.literal("Neo2"))
            //     , Relation.column("age").isEqualTo(QueryBuilder.literal(22))).allowFiltering()
                ;
        List<Users> retrievedBook = cassandraTemplate.select(select.build(), Users.class);
        return retrievedBook;
    }
}
