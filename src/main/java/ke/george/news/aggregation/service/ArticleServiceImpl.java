package ke.george.news.aggregation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ke.george.news.aggregation.domain.Article;
import ke.george.news.aggregation.domain.Rating;
import ke.george.news.aggregation.repository.ArticleRepository;
import ke.george.news.aggregation.web.NotFoundException;

/**
 * Default implementation for article service
 *
 * @author george
 */
@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Fetch a list of articles from the database
     * @param pageable
     * @return
     */
    @Override
    public Page<Article> findArticlesByScore(Pageable pageable) {
        return articleRepository.findByOrderByScoreDesc(pageable);
    }

    /**
     * Get one article from the database
     * @param articleId
     * @return
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Article findOneById(Long articleId) throws NotFoundException {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException("Article not found"));
        article.incrementNoOfPageViews();
        articleRepository.save(article);

        return article;
    }

    /**
     * Find articles and generate a Z-Score for each
     * per the scheduled time
     *
     */
    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void generateScores() {
        List<Article> articles = articleRepository.findByOrderByNoOfPageViewsDesc();
        LOG.info("Generating z-scores for {} articles at {}", articles.size(), LocalDateTime.now());
        for (Article article : articles) {
            Rating rating = new Rating(articles, article);
            double score = rating.getZScore();
            article.setScore(score);
            articleRepository.save(article);
            LOG.info("Updated the score for {}, with a z-score of {}", article.getTitle(), score);
        }
        LOG.info("Finished generating z-scores for {} articles at {}", articles.size(), LocalDateTime.now());
    }
}
