package com.example.vahakassignment.api

import com.example.vahakassignment.models.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IApiRequest
{
    @GET("api.php?")
    fun searchApi(@Query("action")  action:String, @Query("format")  format:String,
                       @Query("prop") prop:String ,
                       @Query("generator")  generator:String,
                       @Query("redirects")  redirects:String,
                       @Query("formatversion")  formatversion:String,
                       @Query("piprop")  piprop:String,
                       @Query("pithumbsize")  pithumbsize:String,
                       @Query("pilimit")  pilimit:String,
                       @Query("wbptterms")  wbptterms:String,
                       @Query("gpssearch")  gpssearch:String,
                       @Query("gpslimit")  gpslimit:String): Call<Example>
}