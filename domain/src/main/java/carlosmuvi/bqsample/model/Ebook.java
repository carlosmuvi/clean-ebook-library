package carlosmuvi.bqsample.model;

/**
 * Created by carlos.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * Created by carlos.
 */
public class Ebook implements Serializable {

    private String title;
    private String author;
    private String path;
    private byte[] cover;
    private Date created;

    public Ebook() {
    }

    public Ebook(String title, String author, String path, byte[] cover, Date created) {
        this.title = title;
        this.author = author;
        this.path = path;
        this.cover = cover;
        this.created = created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }
}

