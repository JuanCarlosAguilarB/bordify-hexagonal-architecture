package com.bordify.shared.domain;

import java.util.List;

public class PageResult<T> {

    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;

    public PageResult(
            List<T> content,
            int pageNumber,
            int pageSize,
            long totalElements)
    {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }



    public List<T> getContent() {
        return this.content;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return 0;
    }
}
