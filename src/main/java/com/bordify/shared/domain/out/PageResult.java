package com.bordify.shared.domain.out;

import java.util.List;

public interface PageResult<T> {

    public List<T> getContent();
    public int getPageNumber();
    public int getPageSize();
    public long getTotalElements();
    public int getTotalPages();

}
