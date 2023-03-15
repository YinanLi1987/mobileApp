
package com.example.myapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Pho(

    var id:Int,
    var author:String,
    var width:Int,
    var height:Int,
    var url:String,
    var download_url:String
)
const val BASE_URL = "https://picsum.photos"
interface PhotosApi{
    @GET("v2/list?page=2&limit=3")
    suspend fun getPhotos002():List<Pho>

    companion object {
        var photosService:PhotosApi? = null

        fun getInstance():PhotosApi{
            if (photosService===null){
                photosService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PhotosApi::class.java)
            }
            return photosService !!
        }
    }
}