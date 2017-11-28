package org.liujia.drools.bean;

/**
 * Created by liujia on 2017/11/27.
 */
public class Behavior {
    private String schema;
    private String dimension;
    private Integer anomalyScore;
    private Integer count;

    public Behavior(String schema, String dimension, Integer anomalyScore, Integer count) {
        this.schema = schema;
        this.dimension = dimension;
        this.anomalyScore = anomalyScore;
        this.count = count;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Integer getAnomalyScore() {
        return anomalyScore;
    }

    public void setAnomalyScore(Integer anomalyScore) {
        this.anomalyScore = anomalyScore;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void out (String s) {
        System.out.println(s);
    }
}
