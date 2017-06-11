package ke.george.news.aggregation;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("unittest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NewsAggregationServiceApplication.class)
public class NewsAggregationServiceApplicationTests {
    @Value("${local.server.port:0}")
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void contextLoads() {
    }
}
