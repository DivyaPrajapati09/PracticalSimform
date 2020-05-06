package com.example.divyapractical.main_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.divyapractical.R
import com.example.divyapractical.database.User
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class UserListAdapter(val context: Context, val users: List<User>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[holder.adapterPosition]
        holder.txtName.text = user.name
        holder.txtDob.text = getFormattedDate(user.dateOfBirth)
        holder.txtEmail.text = user.email
        Picasso.get().load(user.image).into(holder.imProfile)
    }

    private fun getFormattedDate(dateOfBirth: String): String? {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val simpleDateFormatToParse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val date = simpleDateFormat.format(simpleDateFormatToParse.parse(dateOfBirth))
        return date
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imProfile = itemView.findViewById<AppCompatImageView>(R.id.im_profile)
        val txtName = itemView.findViewById<AppCompatTextView>(R.id.txt_name)
        val txtDob = itemView.findViewById<AppCompatTextView>(R.id.txt_dob)
        val txtEmail = itemView.findViewById<AppCompatTextView>(R.id.txt_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_user, parent, false)
        return UserViewHolder(view)
    }
}