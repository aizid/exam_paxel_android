package com.aizidev.examapps.repository

import androidx.lifecycle.LiveData
import com.aizidev.examapps.AppExecutors
import com.aizidev.examapps.api.ApiService
import com.aizidev.examapps.db.dao.EmployeeDao
import com.aizidev.examapps.util.NetworkBoundResource
import com.aizidev.examapps.vo.DataItem
import com.aizidev.examapps.vo.EmployeeRes
import com.aizidev.examapps.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalRepo @Inject constructor(
    private val appExecutors: AppExecutors,
    private val employeeDao: EmployeeDao,
    private val apiService: ApiService
) {

    fun loadEmployee(forceRefresh: Boolean = false): LiveData<Resource<List<DataItem>>> {
        return object : NetworkBoundResource<List<DataItem>, EmployeeRes>(appExecutors) {
            override fun saveCallResult(item: EmployeeRes) {
                employeeDao.insertEmployees(item.data)
            }

            // isAddressesCacheUpToDate() checks the lifetime of cached data
            override fun shouldFetch(data: List<DataItem>?): Boolean
                    = data == null || data.isEmpty() || forceRefresh

            override fun loadFromDb() = employeeDao.loadEmployee()

            override fun createCall() = apiService.getEmployee()
        }.asLiveData()
    }
}