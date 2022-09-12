package com.example.templates.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListener extends JobExecutionListenerSupport {
    @Override
	public void afterJob(JobExecution jobExecution) {
        switch (jobExecution.getStatus()) {
            case ABANDONED:
                break;
            case COMPLETED:
                System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
                break;
            case FAILED:
                break;
            case STARTED:
                break;
            case STARTING:
                break;
            case STOPPED:
                break;
            case STOPPING:
                break;
            case UNKNOWN:
                break;
            default:
                break;

        }
	}
}
