package com.disertatie.rent.car.job;

import com.disertatie.rent.car.recommender.Recommender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RecommenderJob {

    private static final Logger LOG = LoggerFactory.getLogger(RecommenderJob.class);

    @Resource(name = "recommender")
    private Recommender recommender;

    public RecommenderJob() {

    }

    // @Scheduled(cron = "* 20 16 * 6 ?", zone = "Europe/Bucharest") - sambata la 4 pm si 20 min
    @Scheduled(cron = "* 5 17 * 6 ?", zone = "Europe/Bucharest")  // sambata la 5 pm si 17 min
    public void create() {
        LOG.info("Recommender Job started!");
        recommender.calculateSimilarity();
        LOG.info("Recommender Job ended!");
    }
}