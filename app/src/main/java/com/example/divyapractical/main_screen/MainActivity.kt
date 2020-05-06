package com.example.divyapractical.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.divyapractical.R
import com.example.divyapractical.database.DatabaseClass
import com.example.divyapractical.database.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listOfUsers: ArrayList<User>? = ArrayList()
    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        val adapter = UserListAdapter(this, listOfUsers!!)
        rv_user_list.layoutManager = LinearLayoutManager(this)
        rv_user_list.adapter = adapter

        val dbClass = DatabaseClass.getDatabaseInstance(this)
        userViewModel?.listOfUserData?.observe(this, Observer {
            listOfUsers!!.clear()
            listOfUsers!!.addAll(it)
            adapter.notifyDataSetChanged()
        })
        userViewModel?.getAllUsersFromDb(dbClass,this)
    }
}