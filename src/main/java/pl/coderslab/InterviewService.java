package pl.coderslab;

import java.util.List;

public interface InterviewService {


    void saveQuestionsAndAnswersToDb(List<QuestionFromAPI> questionFromAPIList);

    QuestionEntity findQuestionByAnswer (AnswerEntity answerEntity);

    AnswerResponse checkIfAnswersAreCorrect (AnswerPostDTO answerPostDTO);



}
