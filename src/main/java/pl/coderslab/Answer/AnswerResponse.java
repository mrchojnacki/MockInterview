package pl.coderslab.Answer;

public class AnswerResponse {
    private boolean correct;

    public AnswerResponse(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

}
