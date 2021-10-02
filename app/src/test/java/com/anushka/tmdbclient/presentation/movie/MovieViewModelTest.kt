package com.anushka.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.FakeMovieRepository
import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMoviesUsecase
import com.anushka.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// LiveData에 대한 테스트
@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{

    // 테스트 규칙 정의
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        // 테스트 환경에서 사용하기 위한 Fake 객체를 만든다
        // GetMoviesUseCase, UpdateMoviesUsecase 객체를 만들기 위해
        // repository 객체가 매개변수로 필요함
      val fakeMovieRepository = FakeMovieRepository()
        // MovieViewModel은 GetMoviesUseCase, UpdateMoviesUsecase를 매개변수로 받는다
        // 즉, MovieViewModel의 매개변수로 넣기 위해 Fake로 테스트용 객체를 만들 필요 있음
      val getMoviesUsecase = GetMoviesUseCase(fakeMovieRepository)
      val updateMoviesUsecase = UpdateMoviesUsecase(fakeMovieRepository)
      viewModel = MovieViewModel(getMoviesUsecase,updateMoviesUsecase)
    }

    //
    @Test
    fun getMovies_returnsCurrentList(){
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview1", "path1", "date1", "title1"))
        movies.add(Movie(2, "overview2", "path2", "date2", "title2"))

        // 원래 getMoviews() 함수를 return 하면 LiveData<List<Movie?>>를 가져오게 된다
        // 하지만 테스트에서 비교를 하기 위해서는 일반적인 List<Movie?>로 변환할 필요가 있음
        // -> getOrAwaitValue() = liveData 확장함수를 만들어서 직접 정의해줌
        // -> LiveDataTestUtil.kt에 정의함 = 이 파일은 복사해서 다른 프로젝트에 사용해도됨 = 보일러플레이트
        // => getOrAwaitValue() = liveData를 일반저인 데이터 형태로 바꿔주는 함수
        val currentList = viewModel.getMovies().getOrAwaitValue()
        // 테스트 결과가 반환받은 결과와 같은지 비교
        assertThat(currentList).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsUpdatedList(){
        val movies = mutableListOf<Movie>()
        movies.add(Movie(3, "overview3", "path3", "date3", "title3"))
        movies.add(Movie(4, "overview4", "path4", "date4", "title4"))

        val updatedList = viewModel.updateMovies().getOrAwaitValue()
        assertThat(updatedList).isEqualTo(movies)
    }









}









