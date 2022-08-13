package hu.progmatic.webpage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;

    private String content;

    private LocalDateTime created;

    public Page() {

    }

    public Page(String slug, String title, String content) {
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.created = LocalDateTime.now();
    }

    public Page(Long id, String slug, String title, String content, LocalDateTime created) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.created = created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                '}';
    }
}
