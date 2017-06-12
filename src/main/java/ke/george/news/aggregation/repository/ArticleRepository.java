package ke.george.news.aggregation.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ke.george.news.aggregation.domain.Article;

/**
 * Repository class for Article
 *
 * @author george
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public Optional<Article> findById(Long id);
    public Page<Article> findByOrderByScoreDesc(Pageable pageable);
}
