package org.example.mockexam.configs.scheduler;

import org.example.mockexam.configs.util.LoggerUtil;
import org.example.mockexam.repositories.ListeningTestRepository;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ListeningScheduler {
    private final Logger log = LoggerUtil.log(ListeningScheduler.class);
    private final ListeningTestRepository listeningTestRepository;

    public ListeningScheduler(ListeningTestRepository listeningTestRepository) {
        this.listeningTestRepository = listeningTestRepository;
    }

    @Scheduled(cron = "0 55 8 ? * SUN")
    public void activateListeningTest() {
        log.info(
                "---------------------------------LISTENING TEST ACTIVATING PROCESS STARTED {} ------------------------------",
                LocalDateTime.now()
        );

        String activateDate = LocalDate.now() +
                " 09:00:00";
        listeningTestRepository.activateListeningTest(activateDate);

        log.info(
                "---------------------------------LISTENING TEST ACTIVATING PROCESS FINISHED {}  ------------------------------",
                LocalDateTime.now()
        );
    }

    @Scheduled(cron = "0 05 10 ? * SUN")
    public void disActivateListeningTest() {
        log.info(
                "---------------------------------LISTENING TEST DIS ACTIVATING PROCESS STARTED {} ------------------------------",
                LocalDateTime.now()
        );

        listeningTestRepository.disActivateListeningTest();

        log.info(
                "---------------------------------LISTENING TEST DIS ACTIVATING PROCESS FINISHED {}  ------------------------------",
                LocalDateTime.now()
        );
    }
}
