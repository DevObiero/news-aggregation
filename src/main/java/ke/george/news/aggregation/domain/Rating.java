package ke.george.news.aggregation.domain;

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
    private double[] data;
    private Double numOfPageViews;
    private DescriptiveStatistics stats;

    public Rating(double[] data, double numOfPageViews) {
        this.data = data;
        this.numOfPageViews = numOfPageViews;
        this.stats = new DescriptiveStatistics();
        this.setData();
    }

    private void setData() {
        for (double aData : data) {
            stats.addValue(aData);
        }
    }

    private double getMean() {
        return stats.getMean();
    }


    private double getStdDev() {
        return stats.getStandardDeviation();
    }

    public double getZScore() {
        return (numOfPageViews - getMean()) / getStdDev();
    }
}
