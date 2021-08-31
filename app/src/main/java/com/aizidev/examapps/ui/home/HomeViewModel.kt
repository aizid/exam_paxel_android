package com.aizidev.examapps.ui.home

import androidx.lifecycle.*
import com.aizidev.examapps.AppExecutors
import com.aizidev.examapps.repository.GlobalRepo
import com.aizidev.examapps.util.AbsentLiveData
import com.aizidev.examapps.util.AppDispatchers
import com.aizidev.examapps.vo.DataItem
import com.aizidev.examapps.vo.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel
@Inject constructor(val globalRepo: GlobalRepo) : ViewModel() {
    private val _retryVal = MutableLiveData<String>()
    val retryVal: LiveData<String> = _retryVal

    var employee: LiveData<Resource<List<DataItem>>> = _retryVal.switchMap { input ->
        if (input.isBlank()) {
            AbsentLiveData.create()
        } else {
            globalRepo.loadEmployee(false)
        }
    }

    fun retry() {
        _retryVal.value?.let {
            _retryVal.value = it
        }
    }

    fun setupRetry(update: String) {
        if (_retryVal.value == update) {
            return
        }
        _retryVal.value = update
    }
}