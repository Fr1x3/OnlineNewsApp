package com.fr1x3.onlinenewsapp.util

import android.os.Build
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateFormatter {

    companion object f{

        fun getFormattedDate(  publishedAt: String?): String? {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val parsedDate = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                return parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            }
//        }else{
//            val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
//            return formatter.format(parser.parse(publishedAt))
//        }

            return ""
        }
    }


}