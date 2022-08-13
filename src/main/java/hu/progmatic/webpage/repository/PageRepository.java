package hu.progmatic.webpage.repository;

import hu.progmatic.webpage.model.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PageRepository extends CrudRepository<Page, Long> {
    Optional<Page> findBySlug(String slug);
}
