package com.session04.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageResponse<T> {
    List<T> items;
    int page;
    int size;
    long totalItems;
    int totalPages;
    boolean isLast;
}
