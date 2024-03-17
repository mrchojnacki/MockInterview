package pl.coderslab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Answer.AnswerPostDTO;
import pl.coderslab.Answer.AnswerResponse;
import pl.coderslab.Question.QuestionGetDTO;

@RestController
public class MockInterviewController {

    private InterviewServiceImpl interviewService;

    public MockInterviewController(InterviewServiceImpl interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping("/api/questions")
    public QuestionGetDTO getQuestion() {
        return interviewService.getRandomQuestion();
    }

    @PostMapping("/api/answers")
    public AnswerResponse checkIfCorrect(String jsonResponse) throws JsonProcessingException {

        return interviewService.checkIfAnswersAreCorrect(jsonResponse);
    }

}
