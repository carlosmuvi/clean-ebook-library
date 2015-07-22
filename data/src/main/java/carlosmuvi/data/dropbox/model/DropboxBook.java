package carlosmuvi.data.dropbox.model;

/**
 * Created by carlos.
 */

import java.util.Date;

import nl.siegmann.epublib.domain.Book;

/**
 * Created by carlos.
 */
public class DropboxBook {

    private Book book;
    private Date created;
    private String path;

    public DropboxBook() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
