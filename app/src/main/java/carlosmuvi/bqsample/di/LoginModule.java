package carlosmuvi.bqsample.di;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.data.dropbox.DropboxDatasource;
import carlosmuvi.data.dropbox.mapper.DropboxBookMapper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */
@Module
public class LoginModule {

    //PRESENTER

    //USECASES

    //DATASOURCE AND MAPPER
    @Provides
    EbookDatasource provideEbookDatasource(DropboxDatasource dropboxDatasource) {
        return dropboxDatasource;
    }
}
