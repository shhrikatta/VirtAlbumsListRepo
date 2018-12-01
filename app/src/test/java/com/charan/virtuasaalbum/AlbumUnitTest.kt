package com.charan.virtuasaalbum

import android.arch.lifecycle.Observer
import com.charan.virtuasaalbum.db.AlbumDao
import com.charan.virtuasaalbum.network.ApiInterface
import com.charan.virtuasaalbum.ui.model.Album
import com.charan.virtuasaalbum.ui.repository.AlbumRepository
import com.charan.virtuasaalbum.ui.viewmodel.AlbumViewModel
import com.charan.virtuasaalbum.utils.Constants
import com.charan.virtuasaalbum.utils.Utils
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class AlbumUnitTest {

    @Mock
    lateinit var albumViewModel: AlbumViewModel

    @Mock
    lateinit var albumRepository: AlbumRepository

    @Mock
    lateinit var apiInterface: ApiInterface

    @Mock
    lateinit var albumDao: AlbumDao

    @Mock
    lateinit var utils: Utils

    @Mock
    lateinit var observer: Observer<Album>

    @Before
    fun setup() {
        albumRepository = AlbumRepository(apiInterface, albumDao, utils)
        albumViewModel = AlbumViewModel(albumRepository)
    }

    @Test
    fun addition_isCorrect() {
        Mockito.`when`(apiInterface.getAlbumList(Constants.START_ZERO_VALUE))
    }
}