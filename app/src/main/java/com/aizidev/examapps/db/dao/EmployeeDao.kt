package com.aizidev.examapps.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aizidev.examapps.vo.DataItem

@Dao
abstract class EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEmployees(employeeRes: List<DataItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEmployee(employee: DataItem)

    @Query("SELECT * FROM DataItem")
    abstract fun loadEmployee(): LiveData<List<DataItem>>

    @Query("SELECT * FROM DataItem WHERE id = :id")
    abstract fun loadDetailEmployee(id: Int): LiveData<DataItem>
}