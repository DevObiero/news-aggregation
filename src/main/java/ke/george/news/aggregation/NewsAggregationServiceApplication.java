package ke.george.news.aggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableScheduling
public class NewsAggregationServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NewsAggregationServiceApplication.class);
        app.run(args);
    }
}
