package pl.coderslab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.Answer.*;
import pl.coderslab.Errors.InvalidDataException;
import pl.coderslab.Question.QuestionGetDTO;
import pl.coderslab.Question.QuestionEntity;
import pl.coderslab.Question.QuestionFromAPI;
import pl.coderslab.Question.QuestionRepository;

import java.util.ArrayList;
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

    //There is an assumption of increasing IDs in DataBase, without changing or removing picked IDs. Although, in this
    //case/project a new DataBase is created each time the application starts and there are new main key IDs created
    //apart from questionIDs from API. Despite that, there can be implemented a check if ID with randomly generated number
    //exists before getting question with given ID from list of questions
    @Override
    public QuestionGetDTO getRandomQuestion() {
        List<QuestionEntity> allQuestions = questionRepository.findAll();
        int range = allQuestions.size();
        QuestionEntity randomQuestion = allQuestions.get((int)(Math.random() * range));
        List<AnswerGetDTO> answersToShow = getAnswerDTOListForQuestion(randomQuestion.getId());
        return new QuestionGetDTO(randomQuestion.getApiId(), randomQuestion.getQuestion(), answersToShow);
    }

    private List<AnswerGetDTO> getAnswerDTOListForQuestion(Long questionId) {
        List<AnswerEntity> answerEntitiesForQuestion = answerRepository.findAllByQuestionId(questionId);
        List<AnswerGetDTO> answerGetDTOList = new ArrayList<>();
        for (AnswerEntity answer : answerEntitiesForQuestion) {
            AnswerGetDTO answerGetDTO = new AnswerGetDTO(answer.getId(), answer.getAnswer());
            if (answerGetDTO.getAnswer()!=null) {
                answerGetDTOList.add(answerGetDTO);
            }
        }
        return answerGetDTOList;
    }

    private AnswerPostDTO changeJsonToAnswerPostDTO(String jsonResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, AnswerPostDTO.class);
    }

    @Override
    public AnswerResponse checkIfAnswersAreCorrect(String jsonResponse) throws JsonProcessingException {
        AnswerPostDTO answerPostDTO = changeJsonToAnswerPostDTO(jsonResponse);
        if (answerPostDTO.getQuestionId() == null) {
            throw new InvalidDataException("Brakuje wartości questionId.");
        }
        if (answerPostDTO.getAnswers() == null || answerPostDTO.getAnswers().isEmpty()) {
            throw new InvalidDataException("Brakuje odpowiedzi.");
        }
        Long questionId = answerPostDTO.getQuestionId();
        List<AnswerEntity> possibleAnswersForQuestion = answerRepository.findAllByQuestionId(questionId);
        int noOfCorrectAnswersForQuestion = 0;
        int noOfGivenCorrectAnswers = 0;
        for (AnswerEntity answer : possibleAnswersForQuestion) {
            if (answer.isCorrect()) {
                noOfCorrectAnswersForQuestion++;
            }
            // I'm not sure if JSON will give answers given through ID of answer or content of answer.
            // If it had been an ID, it would compare ID through "==" operator
            for (String givenAnswer : answerPostDTO.getAnswers()) {
                if (givenAnswer.equals(answer.getAnswer())) {
                    noOfGivenCorrectAnswers++;
                }
            }
        }
        return new AnswerResponse(noOfGivenCorrectAnswers==noOfCorrectAnswersForQuestion);
    }
}
