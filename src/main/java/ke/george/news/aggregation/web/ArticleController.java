package ke.george.news.aggregation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ke.george.news.aggregation.domain.Article;
import ke.george.news.aggregation.service.ArticleService;

/**
 * Controller class to serve articles to the client side
 *
 * @author george
 */
@RestController
@RequestMapping(value = "articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * Get a list of articles
     *
     * @param pageable object
     * @return a page of articles
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<Article> getArticles(Pageable pageable) {
        LOG.debug("Finding all article entries");
        Page<Article> articles = articleService.findAll(pageable);
        LOG.debug("Found {} article entries", articles.getNumberOfElements());

        return articles;
    }

    /**
     * Get an article given it's id
     *
     * @param articleId for an existing entry
     * @return an article if present
     * @throws NotFoundException if absent
     */
    @RequestMapping(value = "{articleId}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable("articleId") Long articleId) throws NotFoundException {
        LOG.debug("Finding an article with id: {}", articleId);
        return articleService.findOneById(articleId);
    }
}
