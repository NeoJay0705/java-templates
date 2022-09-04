package com.example.templates.cassandra;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface RecordRepository extends CassandraRepository<Record, String>  {
    public List<Record> findByDate(String date);
}
