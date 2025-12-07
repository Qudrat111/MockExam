package org.example.mockexam.configs.scheduler;

import org.example.mockexam.configs.util.LoggerUtil;
import org.example.mockexam.repositories.ReadingTestRepository;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ReadingScheduler {
    private final Logger log = LoggerUtil.log(ReadingScheduler.class);
    private final ReadingTestRepository readingTestRepository;

    public ReadingScheduler(ReadingTestRepository readingTestRepository) {
        this.readingTestRepository = readingTestRepository;
    }

    @Scheduled(cron = "0 25 10 ? * SUN")
    public void activateReadingTest() {
        log.info(
                "---------------------------------READING TEST ACTIVATING PROCESS STARTED {} ------------------------------",
                LocalDateTime.now()
        );

        String activateDate = LocalDate.now() +
                " 10:30:00";
        readingTestRepository.activateReadingTest(activateDate);

        log.info(
                "---------------------------------READING TEST ACTIVATING PROCESS FINISHED {}  ------------------------------",
                LocalDateTime.now()
        );
    }

    @Scheduled(cron = "0 35 11 ? * SUN")
    public void disActivateReadingTest() {
        log.info(
                "---------------------------------READING TEST DIS ACTIVATING PROCESS STARTED {} ------------------------------",
                LocalDateTime.now()
        );

        readingTestRepository.disActivateReadingTest();

        log.info(
                "---------------------------------READING TEST DIS ACTIVATING PROCESS FINISHED {}  ------------------------------",
                LocalDateTime.now()
        );
    }
}
