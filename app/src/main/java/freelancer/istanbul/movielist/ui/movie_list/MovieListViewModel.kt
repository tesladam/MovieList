package freelancer.istanbul.movielist.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import freelancer.istanbul.movielist.data.remote.Resource
import freelancer.istanbul.movielist.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieListRepository) : ViewModel() {

    private var page = 1

    private val mPagedList = mutableListOf<MovieListResponseModel.Result>()
    private var mHasNexPage = true

    private val _movie_list_live_data = MutableLiveData<Resource<MovieListResponseModel>>()
    val movie_list_live_data: LiveData<Resource<MovieListResponseModel>> = _movie_list_live_data


    fun refresh(page: Int = 1) {
        this.page = page

        mPagedList.clear()
        mHasNexPage = true

        get_list()
    }

    fun nextPage() {
        if (!mHasNexPage) {
            return
        }
        page++
        get_list()
    }

    private fun get_list() {
        viewModelScope.launch {
            repository.get_movie_list(page).collect{
                when(it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (it.data.results.size < Constants.GET_LIST_COUNT) {
                            mHasNexPage = false
                        }

                        mPagedList.addAll(it.data.results)
                        it.data.results = mPagedList.toList()

                        _movie_list_live_data.value = it
                    }
                    is Resource.Error -> {
                        mHasNexPage = false

                        _movie_list_live_data.value = it
                    }
                }
            }
        }
    }

}