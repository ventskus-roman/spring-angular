package by.roman.ventskus.config;

import by.roman.ventskus.service.Aggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Component
public class Scheduler {

    @Autowired
    private Aggregator aggregator;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void reportCurrentTime() {
        aggregator.aggregate();
    }
}
