package alnero.job4j.grabber;

import alnero.job4j.grabber.dao.Store;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public interface Grab {
    void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException;
}
