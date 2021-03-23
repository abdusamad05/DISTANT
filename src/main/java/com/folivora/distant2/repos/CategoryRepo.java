package com.folivora.distant2.repos;

import com.folivora.distant2.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
