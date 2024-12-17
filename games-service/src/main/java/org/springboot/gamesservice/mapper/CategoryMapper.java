package org.springboot.gamesservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springboot.gamesservice.category.CategoryApp;
import org.springboot.gamesservice.category.CategoryRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryMapper {

    public CategoryApp toCategory(CategoryRequest request) {
        return CategoryApp.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }

}
