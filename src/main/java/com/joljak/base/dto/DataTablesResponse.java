package com.joljak.base.dto;

import java.util.Collections;
import java.util.List;

/**
 * @author Erik van Paassen
 */
public class DataTablesResponse<T> {
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> articleList = Collections.emptyList();

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<T> articleList) {
        this.articleList = articleList;
    }

}
