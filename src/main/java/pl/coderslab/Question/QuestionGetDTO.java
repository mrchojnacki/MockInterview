package pl.coderslab.Question;

import pl.coderslab.Answer.AnswerGetDTO;

import java.util.List;
import java.util.Map;

public class QuestionGetDTO {
    private Long questionId;
    private String question;
    private List<AnswerGetDTO> answers;

    public QuestionGetDTO(Long questionId, String question, List<AnswerGetDTO> answers) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
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
    public List<AnswerGetDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerGetDTO> answers) {
        this.answers = answers;
    }


}
