package pl.coderslab;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.coderslab.Answer.AnswerEntity;
import pl.coderslab.Answer.AnswerPostDTO;
import pl.coderslab.Answer.AnswerResponse;
import pl.coderslab.Question.QuestionGetDTO;
import pl.coderslab.Question.QuestionEntity;
import pl.coderslab.Question.QuestionFromAPI;

import java.util.List;

public interface InterviewService {


    void saveQuestionsAndAnswersToDb(List<QuestionFromAPI> questionFromAPIList);

    QuestionGetDTO getRandomQuestion();


    AnswerResponse checkIfAnswersAreCorrect(String jsonResponse) throws JsonProcessingException;
}
