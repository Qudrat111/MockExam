package org.example.mockexam.services.impl;

import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.ListeningTest;
import org.example.mockexam.modules.entity.ReadingTest;
import org.example.mockexam.modules.mapper.*;
import org.example.mockexam.repositories.*;
import org.example.mockexam.services.TestResultService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    private final ListeningPartRepository listeningPartRepository;
    private final ReadingPassageRepository readingPassageRepository;
    private final WritingTestResultRepository writingTestResultRepository;
    private final WritingTestResultItemRepository writingTestResultItemRepository;
    private final ListeningTestResultRepository listeningTestResultRepository;
    private final ListeningTestResultItemRepository listeningTestResultItemRepository;
    private final ReadingTestResultRepository readingTestResultRepository;
    private final ReadingTestResultItemRepository readingTestResultItemRepository;
    private final ListeningPartMapper listeningPartMapper;
    private final ListeningQuestionMapper listeningQuestionMapper;
    private final JdbcTemplate jdbcTemplate;
    private final ListeningTestMapper listeningTestMapper;
    private final ReadingQuestionMapper readingQuestionMapper;
    private final ReadingPassageMapper readingPassageMapper;
    private final ReadingTestMapper readingTestMapper;
    private final WritingTaskMapper writingTaskMapper;
    private final WritingTestMapper writingTestMapper;

    public TestResultServiceImpl(ListeningPartRepository listeningPartRepository, ReadingPassageRepository readingPassageRepository, WritingTestResultRepository writingTestResultRepository, WritingTestResultItemRepository writingTestResultItemRepository, ListeningTestResultRepository listeningTestResultRepository, ListeningTestResultItemRepository listeningTestResultItemRepository, ReadingTestResultRepository readingTestResultRepository, ReadingTestResultItemRepository readingTestResultItemRepository, ListeningPartMapper listeningPartMapper, ListeningQuestionMapper listeningQuestionMapper, JdbcTemplate jdbcTemplate, ListeningTestMapper listeningTestMapper, ReadingQuestionMapper readingQuestionMapper, ReadingPassageMapper readingPassageMapper, ReadingTestMapper readingTestMapper, WritingTaskMapper writingTaskMapper, WritingTestMapper writingTestMapper) {
        this.listeningPartRepository = listeningPartRepository;
        this.readingPassageRepository = readingPassageRepository;
        this.writingTestResultRepository = writingTestResultRepository;
        this.writingTestResultItemRepository = writingTestResultItemRepository;
        this.listeningTestResultRepository = listeningTestResultRepository;
        this.listeningTestResultItemRepository = listeningTestResultItemRepository;
        this.readingTestResultRepository = readingTestResultRepository;
        this.readingTestResultItemRepository = readingTestResultItemRepository;
        this.listeningPartMapper = listeningPartMapper;
        this.listeningQuestionMapper = listeningQuestionMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.listeningTestMapper = listeningTestMapper;
        this.readingQuestionMapper = readingQuestionMapper;
        this.readingPassageMapper = readingPassageMapper;
        this.readingTestMapper = readingTestMapper;
        this.writingTaskMapper = writingTaskMapper;
        this.writingTestMapper = writingTestMapper;
    }


    @Override
    public List<TestResultResponse> getAll() {
        String testResultQuery = """
                       select u.username         as username,
                              ltr.correct_answer as listening_correct_answer,
                              ltr.wrong_answer   as listening_wrong_answer,
                              rtr.correct_answer as reading_correct_answer,
                              rtr.wrong_answer   as reading_wrong_answer,
                              wtr.overall_score  as writing_score,
                              to_char(coalesce(ltr.created_date,rtr.created_date,wtr.created_date,null),'YYYY-MM-DD') as submitted_date
                       from users u
                                inner join listening_test_result ltr on ltr.user_id = u.id
                                inner join reading_test_result rtr on rtr.user_id = u.id
                                inner join writing_test_result wtr on wtr.user_id = u.id
       
                       order by ltr.id desc
                """;

        return jdbcTemplate.query(testResultQuery, new TestResultRowMapper());
    }

    @Override
    public List<ListeningTestSubmitResponse> getUserListeningTestResults(Long userId) {
        List<ListeningTestSubmitResponse> listeningTestSubmitResponses = new ArrayList<>();

        listeningTestResultRepository
                .findListeningTestResultByUserIdAndDeletedFalse(userId)
                .forEach(listeningTestResult -> {
                            ListeningTest listeningTest = listeningTestResult.getListeningTest();
                            List<ListeningTestPartSubmitResponse> listeningTestPartSubmitResponses = new ArrayList<>();

                            listeningPartRepository
                                    .findListeningPartByListeningTestIdAndDeletedFalseOrderByIdAsc(listeningTest.getId())
                                    .forEach(listeningPart -> {
                                                List<ListeningTestQuestionSubmitResponse> listeningTestQuestionSubmitResponses = new ArrayList<>();

                                                listeningTestResultItemRepository
                                                        .findReadingTestResultItemByListeningPartId(listeningPart.getId())
                                                        .forEach(listeningTestResultItem ->
                                                                listeningTestQuestionSubmitResponses.add(listeningQuestionMapper.toResponse(
                                                                                listeningTestResultItem.getListeningQuestion(),
                                                                                listeningTestResultItem.getIsCorrectAnswer(),
                                                                                listeningTestResultItem.getUserAnswer()
                                                                        )
                                                                )
                                                        );

                                                listeningTestPartSubmitResponses.add(listeningPartMapper.toResponse(
                                                        listeningPart.getTitle(),
                                                        listeningPart.getAudioUrl(),
                                                        listeningPart.getImageUrl(),
                                                        listeningTestQuestionSubmitResponses)
                                                );
                                            }
                                    );

                            listeningTestSubmitResponses.add(listeningTestMapper.toResponse(
                                    listeningTestResult.getCorrectAnswer(),
                                    listeningTestResult.getWrongAnswer(),
                                    listeningTestPartSubmitResponses)
                            );
                        }
                );

        return listeningTestSubmitResponses;
    }

    @Override
    public List<ReadingTestSubmitResponse> getUserReadingTestResults(Long userId) {
        List<ReadingTestSubmitResponse> readingTestSubmitResponses = new ArrayList<>();

        readingTestResultRepository
                .findReadingTestResultByUserIdAndDeletedFalse(userId)
                .forEach(readingTestResult -> {
                            ReadingTest readingTest = readingTestResult.getReadingTest();
                            List<ReadingTestPassageSubmitResponse> readingTestPassageSubmitResponses = new ArrayList<>();

                            readingPassageRepository
                                    .findReadingPassageByReadingTestIdAndDeletedFalseOrderByIdAsc(readingTest.getId())
                                    .forEach(readingPassage -> {
                                                List<ReadingTestQuestionSubmitResponse> readingTestQuestionSubmitResponses = new ArrayList<>();

                                                readingTestResultItemRepository
                                                        .findReadingTestResultItemByReadingPassageId(readingPassage.getId())
                                                        .forEach(readingTestResultItem ->
                                                                readingTestQuestionSubmitResponses.add(readingQuestionMapper.toResponse(
                                                                                readingTestResultItem.getReadingQuestion(),
                                                                                readingTestResultItem.getIsCorrectAnswer(),
                                                                                readingTestResultItem.getUserAnswer()
                                                                        )
                                                                )
                                                        );

                                                readingTestPassageSubmitResponses.add(readingPassageMapper.toResponse(
                                                        readingPassage.getTitle(),
                                                        readingPassage.getContent(),
                                                        readingTestQuestionSubmitResponses)
                                                );
                                            }
                                    );

                            readingTestSubmitResponses.add(readingTestMapper.toResponse(
                                    readingTestResult.getCorrectAnswer(),
                                    readingTestResult.getWrongAnswer(),
                                    readingTestPassageSubmitResponses)
                            );
                        }
                );

        return readingTestSubmitResponses;
    }

    @Override
    public List<WritingTestSubmitResponse> getUserWritingTestResults(Long userId) {
        List<WritingTestSubmitResponse> writingTestSubmitResponses = new ArrayList<>();

        writingTestResultRepository
                .findWritingTestResultByUserIdAndDeletedFalse(userId)
                .forEach(writingTestRsult -> {
                            List<WritingTestTaskSubmitResponse> writingTestTaskSubmitResponses = writingTestResultItemRepository
                                    .findWritingTestResultItemByWritingTestResultIdAndDeletedFalseOrderByIdAsc(writingTestRsult.getId())
                                    .stream()
                                    .map(writingTestResultItem ->
                                            writingTaskMapper.toResponse(
                                                    writingTestResultItem.getWritingTask(),
                                                    writingTestResultItem.getUserAnswer(),
                                                    writingTestResultItem.getScore()
                                            )
                                    )
                                    .toList();

                            writingTestSubmitResponses.add(writingTestMapper.toResponse(
                                            writingTestRsult.getId(),
                                            writingTestRsult.getOverallScore(),
                                            writingTestRsult.getStatus(),
                                            writingTestTaskSubmitResponses
                                    )
                            );
                        }
                );

        return writingTestSubmitResponses;
    }
}
