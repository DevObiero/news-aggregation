package ke.george.news.aggregation.service;

import java.io.IOException;

import com.rometools.rome.feed.synd.SyndEntry;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import ke.george.news.aggregation.domain.Article;
import ke.george.news.aggregation.repository.ArticleRepository;

/**
 * Implementation class to fetch and save RSS feeds form New York Times website
 *
 * @author george
 */
@MessageEndpoint
public class RssServiceImpl implements RssService {

    private static final Logger LOG = LoggerFactory.getLogger(RssServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    /**
     *  For every Rss feed, fetch the corresponding documents
     *  and persist in the database
     *
     * @param message
     * @throws IOException
     */
    @Override
    @ServiceActivator(inputChannel = RssFeed.channel)
    public void saveFeeds(Message<SyndEntry> message) throws IOException {
        SyndEntry payload = message.getPayload();
        String articleLink = payload.getLink();
        LOG.info("Fetching {} by {}", payload.getTitle(), payload.getAuthor());
        String content = Jsoup.connect(articleLink).get().html();
        articleRepository.save(new Article(payload.getTitle(), payload.getAuthor(), articleLink, content));
        LOG.info("Found and saved {} by {}", payload.getTitle(), payload.getAuthor());
    }
}
