package pl.coderslab;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class InterviewServiceImpl implements InterviewService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public InterviewServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public void saveQuestionsAndAnswersToDb(List<QuestionFromAPI> questionFromAPIList) {
        for (QuestionFromAPI questionFromAPI : questionFromAPIList) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setApiId(questionFromAPI.getQuestionId());
            questionEntity.setQuestion(questionFromAPI.getQuestion());
            questionRepository.save(questionEntity);
            for (Map.Entry<String, String> answerEntry : questionFromAPI.getAnswersText().entrySet()) {
                AnswerEntity answerEntity = new AnswerEntity();
                for (Map.Entry<String, Boolean> correctEntry : questionFromAPI.getCorrectAnswers().entrySet()) {
                    if (correctEntry.getKey().equals((answerEntry.getKey()+"_correct"))) {
                        answerEntity.setCorrect(correctEntry.getValue());
                    }
                }
                answerEntity.setAnswer(answerEntry.getValue());
                answerEntity.setQuestionEntity(questionEntity);
                answerRepository.save(answerEntity);
            }
        }
    }

    @Override
    public QuestionEntity findQuestionByAnswer(AnswerEntity answerEntity) {
        return questionRepository.findQuestionEntityByQuestionId(answerEntity.getQuestionEntity().getApiId());
    }

    @Override
    public AnswerResponse checkIfAnswersAreCorrect(AnswerPostDTO answerPostDTO) {
        Long questionId = answerPostDTO.getQuestionId();
        List<AnswerEntity> possibleAnswersForQuestion = answerRepository.findAllByQuestionId(questionId);
        int noOfCorrectAnswersForQuestion = 0;
        int noOfGivenCorrectAnswers = 0;
        for (AnswerEntity answer : possibleAnswersForQuestion) {
            if (answer.isCorrect()) {
                noOfCorrectAnswersForQuestion++;
            }
            for (String givenAnswer : answerPostDTO.getAnswers()) {
                if (givenAnswer.equals(answer.getAnswer())) {
                    noOfGivenCorrectAnswers++;
                }
            }
        }
        return new AnswerResponse(noOfGivenCorrectAnswers==noOfCorrectAnswersForQuestion);
    }
}
