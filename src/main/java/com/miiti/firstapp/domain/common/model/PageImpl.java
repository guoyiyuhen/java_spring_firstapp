package com.miiti.firstapp.domain.common.model;

import java.util.Iterator;
import java.util.List;

public class PageImpl<Entity> implements Page<Entity>, Iterable<Entity> {

    private List<Entity> content;

    private int pageNumber;

    private int pageSize;

    private long totalNumberOfElements;

    public PageImpl(List<Entity> content) {
        this.content = content;
    }

    public PageImpl(List<Entity> content, int pageNumber, int pageSize, long totalNumberOfElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalNumberOfElements = totalNumberOfElements;
    }

    @Override
    public List<Entity> getContent() {
        return content;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getNumberOfElements() {
        return content.size();
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getTotalNumberOfElements() {
        return totalNumberOfElements;
    }

    @Override
    public int getTotalPages() {
        if (getTotalNumberOfElements() == 0) {
            return 0;
        }
        if (getPageSize() == 0) {
            return 1;
        }

        int totalPages = (int) (getTotalNumberOfElements() / getPageSize());
        if (getTotalNumberOfElements() % getPageSize() > 0) {
            totalPages++;
        }

        return totalPages;
    }

    @Override
    public boolean hasNextPage() {
        return (getPageNumber() < getTotalPages());
    }

    @Override
    public boolean hasPreviousPage() {
        return (getPageNumber() > 1);
    }

    @Override
    public boolean isFirstPage() {
        return (getPageNumber() == 1);
    }

    @Override
    public boolean isLastPage() {
        return (getPageNumber() == getTotalPages());
    }

    @Override
    public Iterator<Entity> iterator() {
        return content.iterator();
    }
}
