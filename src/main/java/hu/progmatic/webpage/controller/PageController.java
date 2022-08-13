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

    // 1. /pages endpoint
    // listázza ki az összes oldalt
    // 1.1 endpoint létrehozása
    // 1.2 nem kell új query
    // (de lehet, ha nagyon szeretnénk, de a CrudRepository-ban van erre szolgáló query).
    // 1.3 pages view létrehozása

    // 2. /pages/new endpoint
    // listázza ki az utolsó 48 órában létrehozott oldalakat
    // -> LocalDateTimw.now.minusDays(2)
    // 2.1 endpoint létrehozása
    // 2.2 új query létrehozása (amilyet szeretnétek, de a generált a legkönnyebb)
    // 2.3 nem kell új view (lehet pages view-t használni)

    // 3. az eddigi endpointoknál dinamikusan jelenjen meg a menü (nehéz feladat)
    // + title szerint rendezve
    // - query létrehozása
    // - le kellene kérdezni az oldalakat
    // - model.addAttribute("pages", pages)
    // - összes view-t meg kell változtatni a menünél

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

}
