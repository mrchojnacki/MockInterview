package pl.coderslab.App;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.AnswerRepository;
import pl.coderslab.InterviewServiceImpl;
import pl.coderslab.QuestionRepository;

@Configuration
public class AppConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
