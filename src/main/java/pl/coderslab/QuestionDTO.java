package pl.coderslab;

import java.util.List;
import java.util.Map;

public class QuestionDTO {
    private Long questionId;
    private String question;
    private Map<String, String> answersText;

    public QuestionDTO(Long questionId, String question, Map<String, String> answersText) {
        this.questionId = questionId;
        this.question = question;
        this.answersText = answersText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, String> getAnswersText() {
        return answersText;
    }

    public void setAnswersText(Map<String, String> answersText) {
        this.answersText = answersText;
    }
}
