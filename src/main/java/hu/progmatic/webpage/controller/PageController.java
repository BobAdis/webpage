package hu.progmatic.webpage.controller;

import hu.progmatic.webpage.model.Page;
import hu.progmatic.webpage.repository.PageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PageController {

    private final PageRepository pageRepository;

    public PageController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/pages/{slug}")
    public String viewPage(@PathVariable String slug, Model model) {
        Optional<Page> page = pageRepository.findBySlug(slug);
        model.addAttribute("page", page.orElseThrow());

        return "page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

}
