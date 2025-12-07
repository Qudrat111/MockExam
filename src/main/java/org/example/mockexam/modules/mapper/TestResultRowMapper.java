package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.response.TestResultResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultRowMapper implements RowMapper<TestResultResponse> {
    @Override
    public TestResultResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        TestResultResponse testResultResponse = new TestResultResponse();

        testResultResponse.setUsername(rs.getString("username"));
        testResultResponse.setListeningCorrectAnswer(rs.getInt("listening_correct_answer"));
        testResultResponse.setListeningWrongAnswer(rs.getInt("listening_wrong_answer"));
        testResultResponse.setReadingCorrectAnswer(rs.getInt("reading_correct_answer"));
        testResultResponse.setReadingWrongAnswer(rs.getInt("reading_wrong_answer"));
        testResultResponse.setWritingScore(rs.getDouble("writing_score"));
        testResultResponse.setSubmittedDate(rs.getString("submitted_date"));

        return testResultResponse;
    }
}
