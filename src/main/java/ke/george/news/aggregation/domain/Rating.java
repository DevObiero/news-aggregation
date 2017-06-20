package ke.george.news.aggregation.domain;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * @author george
 *
 * Calculate the rating, Z-Score for viewed articles against other articles
 * Standard Score, z = (X - μ)
                        ————
                        σ
 *
 */
public class Rating {
    private List<Article> articles;
    private Article article;
    private DescriptiveStatistics stats;

    public Rating(List<Article> articles, Article article) {
        this.articles = articles;
        this.article = article;
        this.stats = new DescriptiveStatistics();
        this.setData();
    }

    private void setData() {
        for (Article article : articles) {
            stats.addValue(article.getNoOfPageViews());
        }
    }

    private double getMean() {
        return stats.getMean();
    }

    private double getStdDev() {
        return stats.getStandardDeviation();
    }

    public double getZScore() {
        double rating = (article.getNoOfPageViews() - getMean()) / getStdDev();
        return Double.isFinite(rating) ? rating : 0d;
    }
}
