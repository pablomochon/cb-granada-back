package com.basketballticketsproject.basketballticketsproject.scheduler;

import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class RecontarEntradasScheduler {

    @Autowired
    private SorteoRepo sorteoRepo;

    //@Scheduled(cron = "* * * * * *")
    public void contarEntradas() {

    }
}
