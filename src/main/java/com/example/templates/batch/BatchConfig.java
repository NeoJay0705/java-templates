package com.example.templates.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
    @Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

    // @Override
    // @Autowired
    // public void setDataSource(DataSource dataSource) {
    //     super.setDataSource(batchDataSource());
    // }

    // private DataSource batchDataSource(){          
    //    return DataSourceBuilder.create()
    //             .url(env.getProperty("batchdb.url"))
    //             .driverClassName(env.getProperty("batchdb.driver"))
    //             .username(env.getProperty("batchdb.username"))
    //             .password(env.getProperty("batchdb.password"))
    //             .build();          
    // }

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
                .listener(listener())
				.flow(orderStep1())
                .end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1")
                .<String, String> chunk(1)
				.reader(new Reader())
                .processor(new Processor())
				.writer(new Writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}
