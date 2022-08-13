package hu.progmatic.webpage.command;

import hu.progmatic.webpage.model.Page;
import hu.progmatic.webpage.repository.PageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DbInitRunner implements CommandLineRunner {
    private final PageRepository repository;

    public DbInitRunner(PageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Creating test pages...");

        repository.save(new Page("products", "Products", "Hello on the products page!"));
        System.out.println("Products page created.");

        repository.save(new Page(
                null, "about", "About",
                "Hello on the about page!",
                LocalDateTime.now().minusDays(10))
        );
        System.out.println("About page created.");
    }
}
