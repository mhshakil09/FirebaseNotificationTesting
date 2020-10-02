package com.example.firebasenotificationtesting

class History {
    var title: String?= null
    var message: String?=null

    constructor(){}

    constructor( title:String, message: String){
        this.title = title
        this.message = message
    }

}