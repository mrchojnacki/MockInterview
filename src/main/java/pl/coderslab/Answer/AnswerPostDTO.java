package pl.coderslab.Answer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnswerPostDTO {

    public AnswerPostDTO(List<String> answers, Long questionId) {
        this.answers = answers;
        this.questionId = questionId;
    }


    @JsonProperty("answers")
    private List<String> answers;
    @JsonProperty("questionId")
    private Long questionId;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
