# LiveData_UnitTest_U_Test

* ## liveData Unit test 흐름
Project와 같이 보면서 따라가는게 좋음
기타 추가 내용은 Project의 코멘트에 설명이 있음

### 1. MovieViewModel.kt
- getMovies()가 항상 movieList를 가져오는지
- updateMovies()가 항상 movieList를 업데이트하는지 테스트해한다
- 여기서 movieList가 liveData이기 때문에 liveData 유닛 테스트임

### 2. Room 유닛 테스트와 달리 test pakage에서 진행할 거임
- gradle의 androidTestImplementation을 다음으로 바꾼다음 추가한다
-> testImplementation
- 항목 자체는 그대로 사용한다
- testImplementation만 사용하는게 아니라 androidTestImplementation도 사용하기 때문에 둘 다 gradle에 존재해야한다

### 3. 새로운 gradle 추가 = robolectric
- testImplementation "org.robolectric:robolectric:4.4"
- 유닛 테스트를 할 때 단말기를 켜지않고 테스트를 할 수 있게 해준다
- 결과적으로 더욱 빠르게 테스트를 실시하고 결과를 알 수 있다

### 4. test 패키지에 MovieViewModelTest.kt를 만들어준다

### 5. Fake repository 객체를 만들기 위해 패키지를 만들어준다
- data.repository.movie -> FakeMovieRepository

### 6. ViewModelTest.kt에 있는 각 테스트 메서드의 의의
- getMovies_returnsCurrentList(): getMovies()를 통해 가져오는 movieList가 테스트 결과의 movieList와 동일한지 확인
- updateMovies_returnsUpdatedList(): updateMovie()를 통해 가져오는 movieList가 테스트 결과의 movieList와 동일한지 확인

### 7. getMovies() / updateMovies()
- 위 두 함수를 통해 반환 받은 변수는 liveData 형태의 변수임
- 비교를 위해서는 일반 데이터형으로 변환할 필요가 있음
=> LiveDataTestUtil.kt의 getOrAwaitValue()
- 이 파일은 보일러플레이트며 liveData를 일반 변수형으로 바꿔주는 역할
