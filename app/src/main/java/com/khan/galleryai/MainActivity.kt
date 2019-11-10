package com.khan.galleryai

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivate"

    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recylerview)
        showAllImages()
    }

    private fun showAllImages() = runWithPermissions(Manifest.permission.READ_EXTERNAL_STORAGE) {
        ImageUtils.getAllImagePaths(this).observe(this,  Observer {
            Log.i(TAG, "list size: ${it.size}")
            recyclerView?.layoutManager = GridLayoutManager(this, 2)
            adapter = RecyclerViewAdapter(it)
            recyclerView?.adapter = adapter
        })

    }

}
