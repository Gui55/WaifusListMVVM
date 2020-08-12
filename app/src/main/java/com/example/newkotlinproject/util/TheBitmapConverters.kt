package com.example.newkotlinproject.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class TheBitmapConverters {

    companion object{
        val instance = TheBitmapConverters()
    }

    @TypeConverter
    fun BitmapToString(bmp: Bitmap) : String{
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos)

        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
    }

    @TypeConverter
    fun StringToBitmap(string: String) : Bitmap{

        val imBytes = Base64.decode(string, 0)
        return BitmapFactory.decodeByteArray(imBytes, 0, imBytes.size)

    }

}