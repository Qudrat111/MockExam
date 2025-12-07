package org.example.mockexam.configs.scheduler;

import org.example.mockexam.configs.util.LoggerUtil;
import org.example.mockexam.repositories.WritingTestRepository;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class WritingScheduler {
    private final Logger log = LoggerUtil.log(WritingScheduler.class);
    private final WritingTestRepository writingTestRepository;

    public WritingScheduler(WritingTestRepository writingTestRepository) {
        this.writingTestRepository = writingTestRepository;
    }

    @Scheduled(cron = "0 55 11 ? * SUN")
    public void activateWritingTest() {
        log.info(
                "---------------------------------WRITING TEST ACTIVATING PROCESS STARTED {} ------------------------------",
                LocalDateTime.now()
        );

        String activateDate = LocalDate.now() +
                " 12:00:00";
        writingTestRepository.activateWritingTest(activateDate);

        log.info(
                "---------------------------------WRITING TEST ACTIVATING PROCESS FINISHED {}  ------------------------------",
                LocalDateTime.now()
        );
    }

    @Scheduled(cron = "0 05 13 ? * SUN")
    public void disActivateWritingTest() {
        log.info(
                "---------------------------------WRITING TEST DIS ACTIVATING PROCESS STARTED {} ------------------------------",
                LocalDateTime.now()
        );

        writingTestRepository.disActivateWritingTest();

        log.info(
                "---------------------------------WRITING TEST DIS ACTIVATING PROCESS FINISHED {}  ------------------------------",
                LocalDateTime.now()
        );
    }
}
