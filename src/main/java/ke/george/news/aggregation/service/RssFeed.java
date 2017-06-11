package ke.george.news.aggregation.service;

import java.net.MalformedURLException;
import java.net.URL;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

/**
 * Configuration class for the rss feed adapter
 *
 * @author george
 */
@Configuration
@EnableAutoConfiguration
@Profile("!unittest")
public class RssFeed {

    @Value("${feed.rss.url:}")
    private String rssFeedUrl;

    static final String channel = "feedChannel";

    @Bean
    @InboundChannelAdapter(value = channel,
            poller = @Poller(maxMessagesPerPoll = "100", fixedRate = "10000"))
    public MessageSource<SyndEntry> feedAdapter() throws MalformedURLException {
        return new FeedEntryMessageSource(new URL(rssFeedUrl), "feedAdapter");
    }

    @Bean
    public MessageChannel feedChannel() {
        return new QueueChannel(500);
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        PeriodicTrigger trigger = new PeriodicTrigger(10);
        trigger.setFixedRate(true);
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(trigger);
        return pollerMetadata;
    }
}
