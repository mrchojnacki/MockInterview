package pl.coderslab.Question;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.InterviewServiceImpl;

import java.util.List;

@Component
public class QuestionDownloader implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final InterviewServiceImpl interviewService;

    public QuestionDownloader (RestTemplate restTemplate, InterviewServiceImpl interviewService) {
        this.restTemplate = restTemplate;
        this.interviewService = interviewService;
    }

    public void run(String... args) throws Exception {

        String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=tivjF58oDujpLbvLRRG2YPqM2zgGbnFjLZbkXlP6";

        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<QuestionFromAPI> questions = objectMapper.readValue(jsonResponse, new TypeReference<List<QuestionFromAPI>>(){});
        interviewService.saveQuestionsAndAnswersToDb(questions);
    }

}
