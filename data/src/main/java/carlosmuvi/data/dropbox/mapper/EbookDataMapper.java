package carlosmuvi.data.dropbox.mapper;

import android.util.Log;
import carlosmuvi.bqsample.model.Ebook;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DeltaEntry;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import rx.functions.Func1;

/**
 * Created by carlos.
 */
public class EbookDataMapper implements Func1<DeltaEntry<Entry>, Ebook> {

  public static final String DROPBOX_DATEFORMAT = "EEE, d MMM yyyy HH:mm:ss Z";

  DropboxAPI dropboxAPI;

  public EbookDataMapper(DropboxAPI dropboxAPI) {
    this.dropboxAPI = dropboxAPI;
  }

  @Override public Ebook call(DeltaEntry<Entry> entry) {

    Ebook ebook = new Ebook();

    try {
      DropboxAPI.DropboxInputStream file = dropboxAPI.getFileStream(entry.metadata.path, null);

      //try to convert file to epub
      Book epubBook = new EpubReader().readEpub(file);

      //fill epub related info
      Author author = epubBook.getMetadata().getAuthors().get(0);
      ebook.setAuthor(author.getLastname() + ", " + author.getFirstname());
      ebook.setTitle(epubBook.getTitle());
      try {
        ebook.setCover(epubBook.getCoverImage().getData());
      } catch (Exception e) {
        Log.d("DEBUG", ebook.getTitle() + "has no cover");
      }

      //fill file related info
      SimpleDateFormat df = new SimpleDateFormat(DROPBOX_DATEFORMAT, Locale.US);
      ebook.setCreated(df.parse(entry.metadata.clientMtime));
      ebook.setPath(entry.metadata.path);

      return ebook;


    } catch (IOException e) {
      Log.d("DEBUG", entry.metadata.fileName() + ": corrupted or non real epub file, skipping file");
      return null;
    } catch (ParseException e) {
      Log.d("DEBUG", entry.metadata.fileName() + ": error mapping date, skipping ebook");
      return null;
    } catch (DropboxException e) {
      Log.d("DEBUG", entry.metadata.fileName() + ": error downloading file from dropbox");
      return null;
    }
  }
}
