package org.spring.springboot.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJob implements Job{ 
	
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    } 
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException { 
        System.out.println("ScheduledJob: The time is now " + dateFormat().format(new Date())); 
    }
}