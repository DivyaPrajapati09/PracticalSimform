package com.example.divyapractical.main_screen

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.divyapractical.database.DatabaseClass
import com.example.divyapractical.database.User
import com.example.divyapractical.retrofit_setup.ApiClient
import com.example.divyapractical.retrofit_setup.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel : ViewModel() {

    val listOfUserData: MutableLiveData<List<User>> = MutableLiveData()

    fun getAllUserFromApi(dbClass: DatabaseClass) {
        ApiClient().service.getUserList(100).enqueue(object : Callback<UserModel.UserResponse> {
            override fun onFailure(call: Call<UserModel.UserResponse>, t: Throwable) {
                Log.e("TAG", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<UserModel.UserResponse>,
                response: Response<UserModel.UserResponse>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    val listofUserToInsertInDb: ArrayList<User> = ArrayList()
                    users!!.results.map {
                        val name = generateName(it.name)
                        val userInDb =
                            User(
                                dbClass.UserDao().getId(name),
                                name,
                                it.gender,
                                it.email,
                                it.dob.date,
                                it.picture.thumbnail
                            )
                        listofUserToInsertInDb.add(userInDb)
                    }
                    dbClass.UserDao().insertUsers(listofUserToInsertInDb)
                    listOfUserData.postValue(dbClass.UserDao().getAllUser())
                }
            }
        })
    }

    private fun generateName(name: UserModel.Name): String {
        return name.title.plus(" ").plus(name.first).plus(" ").plus(name.last)
    }

    fun getAllUsersFromDb(dbClass: DatabaseClass,context : Context) {
        val user = dbClass.UserDao().getAllUser()
        if(isNetworkConnected(context)) {
            getAllUserFromApi(dbClass)
        }
        listOfUserData.postValue(user)
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }
}