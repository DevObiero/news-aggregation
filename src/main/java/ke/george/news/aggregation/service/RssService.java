package ke.george.news.aggregation.service;

import java.io.IOException;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.messaging.Message;

/**
 * @author george
 */
public interface RssService {
    public void saveFeeds(Message<SyndEntry> message) throws IOException;
}
