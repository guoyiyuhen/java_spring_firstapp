package com.miiti.firstapp.domain.common.model;

import java.util.Iterator;
import java.util.List;

public interface Page<Element> extends Iterable<Element> {

    List<Element> getContent();

    int getPageNumber();

    int getNumberOfElements();

    int getPageSize();

    long getTotalNumberOfElements();

    int getTotalPages();

    boolean hasNextPage();

    boolean hasPreviousPage();

    boolean isFirstPage();

    boolean isLastPage();

    @Override
    Iterator<Element> iterator();
}
