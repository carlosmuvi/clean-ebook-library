package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.model.Ebook;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by carlos.
 */
public class EbookTest {

  private final String FAKE_TITLE = "TITULO";
  private final String FAKE_AUTHOR = "AUTOR";
  private final String FAKE_PATH = "PATH";
  private final byte[] FAKE_PHOTO = new byte[1];
  private final Date FAKE_DATE = new Date(1000);

  Ebook ebook;

  @Before public void setUp() {
    ebook = new Ebook(FAKE_TITLE, FAKE_AUTHOR, FAKE_PATH, FAKE_PHOTO, FAKE_DATE);
  }

  @Test public void testEbookConstructorHappyCase() {
    assertThat(ebook.getAuthor(), is(FAKE_AUTHOR));
    assertThat(ebook.getTitle(), is(FAKE_TITLE));
    assertThat(ebook.getCreated(), is(FAKE_DATE));
    assertThat(ebook.getPath(), is(FAKE_PATH));

  }
}
