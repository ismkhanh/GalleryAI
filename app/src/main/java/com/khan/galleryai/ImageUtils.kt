package com.khan.galleryai

import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData

object ImageUtils {

    val TAG = "ImageUtils"

    fun getAllImagePaths(activity: Activity): MutableLiveData<List<Uri>> {
        var liveData = MutableLiveData<List<Uri>>()
        val uriExternal: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val columnIndexID: Int
        val listOfAllImages: MutableList<Uri> = mutableListOf()
        val projection = arrayOf(MediaStore.Images.Media._ID)
        var imageId: Long
        cursor = activity.contentResolver.query(uriExternal, projection, null, null, null)
        if (cursor != null) {
            columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                imageId = cursor.getLong(columnIndexID)
                val uriImage = Uri.withAppendedPath(uriExternal, imageId.toString())
                Log.i(TAG, "uriImage: $uriImage")
                listOfAllImages.add(uriImage)
            }
            cursor.close()
        }
        Log.i(TAG, "listOfAllImages size: ${listOfAllImages.size}")
        liveData.value = listOfAllImages
        return liveData
    }
}