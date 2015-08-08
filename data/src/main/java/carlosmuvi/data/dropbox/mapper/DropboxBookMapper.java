package carlosmuvi.data.dropbox.mapper;

import android.util.Log;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.data.dropbox.model.DropboxBook;
import javax.inject.Inject;
import nl.siegmann.epublib.domain.Author;
import rx.functions.Func1;

/**
 * Created by carlos.
 */
public class DropboxBookMapper implements Func1<DropboxBook, Ebook> {

  @Inject public DropboxBookMapper() {
  }

  @Override public Ebook call(DropboxBook dataSourceEntity) {
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
}
