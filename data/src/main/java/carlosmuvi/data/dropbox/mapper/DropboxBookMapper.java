package carlosmuvi.data.dropbox.mapper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.data.dropbox.model.DropboxBook;
import nl.siegmann.epublib.domain.Author;

/**
 * Created by carlos.
 */
public class DropboxBookMapper {

    @Inject
    DropboxBookMapper(){

    }

    public Ebook mapFromSourceToModel(DropboxBook dataSourceEntity) {
        Ebook ebook = new Ebook();

        Author author = dataSourceEntity.getBook().getMetadata().getAuthors().get(0);
        ebook.setAuthor(author.getLastname() + ", " + author.getFirstname());

        ebook.setTitle(dataSourceEntity.getBook().getTitle());

        ebook.setCreated(dataSourceEntity.getCreated());

        ebook.setPath(dataSourceEntity.getPath());

        try {
            ebook.setCover(dataSourceEntity.getBook().getCoverImage().getData());
        } catch (Exception e) {
            Log.d("DEBUG", ebook.getTitle() + "has no cover");
        }

        return ebook;
    }

    public List<Ebook> mapFromSourceToModel(List<DropboxBook> entityCollection) {
        List<Ebook> domainEbooks = new ArrayList<>();
        for (DropboxBook dropboxBook : entityCollection) {
            domainEbooks.add(mapFromSourceToModel(dropboxBook));
        }
        return domainEbooks;
    }

}
