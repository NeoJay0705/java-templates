package com.example.templates.cassandra;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("record")
public class RecordController {
    private RecordRepository recordRepository;
    private CassandraService cService;

    public RecordController(RecordRepository recordRepository, CassandraService cService) {
        this.recordRepository = recordRepository;
        this.cService = cService;
    }

    @RequestMapping("all2")
    public Object getAll2() {
        return cService.select3();
    }

    @RequestMapping("insert/{name}/{age}")
    public Object insert(@PathVariable String name, @PathVariable int age) {
        cService.select1(name, age);
        return cService.select3();
    }

    @RequestMapping("del/{name}/{age}")
    public Object delete(@PathVariable String name, @PathVariable int age) {
        cService.select2(name, age);
        return cService.select3();
    }

    @RequestMapping("all")
    public List<Record> getAll() {
        return recordRepository.findAll();
    }

    @RequestMapping("{date}")
    public List<Record> getbyname(@PathVariable String date) {
        return recordRepository.findByDate(date);
    }
}
