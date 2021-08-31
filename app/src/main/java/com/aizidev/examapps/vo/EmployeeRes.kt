package com.aizidev.examapps.vo

import androidx.room.Entity
import androidx.room.TypeConverters
import com.aizidev.examapps.db.Converters
import com.google.gson.annotations.SerializedName

data class EmployeeRes(

	@field:SerializedName("data")
	val data: List<DataItem> = listOf(),

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)


@Entity(primaryKeys = ["id"])
@TypeConverters(Converters::class)
data class DataItem(

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("employee_name")
	val employeeName: String? = null,

	@field:SerializedName("employee_salary")
	val employeeSalary: Int? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("employee_age")
	val employeeAge: Int? = null
)
