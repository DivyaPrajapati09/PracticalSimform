package com.example.divyapractical.retrofit_setup

object UserModel {

    data class User(
        var gender: String,
        var name: Name,
        var email: String,
        var dob: DateOfBirth,
        var picture: Pictures
    )

    data class Name(
        var title: String,
        var first: String,
        var last: String
    )

    data class DateOfBirth(
        var date: String,
        var age: Int
    )

    data class Pictures(
        var large: String,
        var medium: String,
        var thumbnail: String
    )

    data class UserResponse(
        var results: List<User>
    )
}