package alnero.job4j.grabber.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    /** Post id. */
    private int id;
    /** Post title. */
    private String title;
    /** Post url. */
    private String link;
    /** Post contents. */
    private String description;
    /** Post creation date & time. */
    private LocalDateTime created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return getId() == post.getId()
                && Objects.equals(getTitle(), post.getTitle())
                && Objects.equals(getLink(), post.getLink())
                && Objects.equals(getDescription(), post.getDescription())
                && Objects.equals(getCreated(), post.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getLink(), getDescription(), getCreated());
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", created=" + created + '}';
    }
}
