package ke.george.news.aggregation.service;

import com.rometools.rome.feed.synd.SyndEntry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;

import ke.george.news.aggregation.repository.ArticleRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author george
 */
public class RssServiceImplTest {
    @Mock
    private Message<SyndEntry> message;

    @Mock
    private SyndEntry syndEntry;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private RssServiceImpl victim;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveFeeds() throws Exception {
        when(message.getPayload()).thenReturn(syndEntry);
        when(syndEntry.getLink()).thenReturn("http://google.com");
        victim.saveFeeds(message);

        verify(articleRepository, times(1));
    }

}