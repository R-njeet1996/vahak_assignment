package com.example.vahakassignment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vahakassignment.adapter.SearchAdapter
import com.example.vahakassignment.api.ApiCallBack
import com.example.vahakassignment.api.ApiClient
import com.example.vahakassignment.api.IApiRequest
import com.example.vahakassignment.models.Example
import com.example.vahakassignment.models.Page
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(),ItemClickListener {

  private  var data: ArrayList<Page> = ArrayList()
   private lateinit var searchAdapter:SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Init()
    }

    private fun Init() {


            edt_search.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }


                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    if(NetworkConnection.getInstance(this@MainActivity).isConnected)

                        search(edt_search.text.toString())

                    else
                        Toast.makeText(this@MainActivity,"Please check internet connection",Toast.LENGTH_LONG).show()

                }
            })


    }

    fun search(keyWord:String){
        val request: IApiRequest = ApiClient.request!!
        val call: Call<Example> = request.searchApi("query","json","pageterms|pageimages","prefixsearch"
        ,"1","2","thumbnail","50","10","description",keyWord,"10")
        call.enqueue(object : ApiCallBack<Example>(this) {
            override fun onSuccess(example: Example?) {

                if(example!=null) {

                    data.clear()
                    if(example.query!=null) {
                        if(example.query.pages!=null && example.query.pages.size>0 )
                        data.addAll(example.query.pages)
                    }
                    setAdapter()

                }
            }

            override fun onError(error: Error) {

                Toast.makeText(this@MainActivity,"Facing issue in fetching data !!",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setAdapter() {

        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_search.layoutManager = layoutManager
        searchAdapter = SearchAdapter(this,data,this)
        rv_search.adapter = searchAdapter
    }



    override fun onClickItem(pos: Int) {

       var intent  = Intent(this,WebActivity::class.java)
        intent.putExtra("head",data.get(pos).title)
        startActivity(intent)
    }





}