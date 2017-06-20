package ke.george.news.aggregation.service;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ke.george.news.aggregation.PageBuilder;
import ke.george.news.aggregation.domain.Article;
import ke.george.news.aggregation.repository.ArticleRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author george
 */
public class ArticleServiceImplTest {
    @Mock
    private Article article;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl victim;

    private static final Long ARTICLE_ID = 1L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canGetArticles() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Article> page = new PageBuilder<Article>()
                .elements(Lists.newArrayList(new Article("Test Title", "Mark Twain", "http://", ""),
                        new Article("Test Title @", "New Author", "http://", "")))
                .pageRequest(pageRequest)
                .build();
        when(articleRepository.findByOrderByScoreDesc(pageRequest)).thenReturn(page);
        Page<Article> articles = victim.findArticlesByScore(pageRequest);
        verify(articleRepository, times(1)).findByOrderByScoreDesc(pageRequest);

        assertEquals(2, articles.getNumberOfElements());

    }

    @Test
    public void canFindOneArticle() throws Exception {
        when(articleRepository.findById(ARTICLE_ID)).thenReturn(Optional.of(article));
        Article fetchedArticle = victim.findOneById(ARTICLE_ID);
        verify(articleRepository, times(1)).findById(ARTICLE_ID);
        verify(articleRepository, times(1)).save(article);

        assertEquals(article, fetchedArticle);
    }
}