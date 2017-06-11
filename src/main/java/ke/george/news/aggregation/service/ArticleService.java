package ke.george.news.aggregation.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ke.george.news.aggregation.domain.Article;
import ke.george.news.aggregation.web.NotFoundException;

/**
 * @author george
 */
public interface ArticleService {
    public Page<Article> findAll(Pageable pageable);
    public Article findOneById(Long articleId) throws NotFoundException;
}
