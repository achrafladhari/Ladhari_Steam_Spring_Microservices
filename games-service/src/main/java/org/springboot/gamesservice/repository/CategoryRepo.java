package org.springboot.gamesservice.repository;

import org.springboot.gamesservice.category.CategoryApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryApp,Integer> {
}
