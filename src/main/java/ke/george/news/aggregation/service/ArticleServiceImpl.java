package ke.george.news.aggregation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ke.george.news.aggregation.domain.Article;
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
    public Article findOneById(Long articleId) throws NotFoundException {
        return articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException("Article not found"));
    }
}
