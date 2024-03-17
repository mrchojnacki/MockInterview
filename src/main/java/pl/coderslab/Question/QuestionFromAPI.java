package pl.coderslab.Question;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionFromAPI {

    public QuestionFromAPI() {
    }
    public QuestionFromAPI(Long questionId, String question, Map<String, String> answersText, Map<String, Boolean> correctAnswers) {
        this.questionId = questionId;
        this.question = question;
        this.answersText = answersText;
        this.correctAnswers = correctAnswers;
    }

    @JsonProperty("id")
    private Long questionId;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answers")
    private Map<String, String> answersText;
    @JsonProperty("correct_answers")
    private Map<String, Boolean> correctAnswers;

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

    public Map<String, Boolean> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Map<String, Boolean> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }


}
