package com.example.kotlintima.repository

import android.os.Parcelable
import com.example.kotlintima.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
) : Parcelable

fun getDefaultCity() = City("Москва", 55.75, 37.61, R.drawable.ic_london)

@Parcelize
data class City(val name: String, val lat: Double, val lon: Double, val imageCity: Int) : Parcelable
//Добавил в City, а не в Weather, тк все эти значения const

fun getWorldCities(): List<Weather> {
    return listOf(
        Weather(City("Лондон", 51.5085300, -0.1257400, R.drawable.ic_london), 1, 2),
        Weather(City("Токио", 35.6895000, 139.6917100, R.drawable.ic_tokyo), 3, 4),
        Weather(City("Париж", 48.8534100, 2.3488000, R.drawable.ic_paris), 5, 6),
        Weather(City("Берлин", 52.52000659999999, 13.404953999999975, R.drawable.ic_berlin), 7, 8),
        Weather(City("Рим", 41.9027835, 12.496365500000024, R.drawable.ic_rome), 9, 10,),
        Weather(City("Минск", 53.90453979999999, 27.561524400000053, R.drawable.ic_minsk), 11, 12),
        Weather(City("Стамбул", 41.0082376, 28.97835889999999, R.drawable.ic_stambul), 13, 14),
        Weather(City("Вашингтон", 38.9071923, -77.03687070000001, R.drawable.ic_washington), 15, 16),
        Weather(City("Киев", 50.4501, 30.523400000000038, R.drawable.ic_kiev), 17, 18),
        Weather(City("Пекин", 39.90419989999999, 116.40739630000007, R.drawable.ic_pekin), 19, 20)
    )
}

fun getRussianCities(): List<Weather> {
    return listOf(
        Weather(City("Москва", 55.755826, 37.617299900000035, R.drawable.ic_moscow), 1, 2),
        Weather(City("Санкт-Петербург", 59.9342802, 30.335098600000038, R.drawable.ic_saint_peter), 3, 3),
        Weather(City("Новосибирск", 55.00835259999999, 82.93573270000002, R.drawable.ic_russia), 5, 6),
        Weather(City("Екатеринбург", 56.83892609999999, 60.60570250000001, R.drawable.ic_russia), 7, 8),
        Weather(City("Нижний Новгород", 56.2965039, 43.936059, R.drawable.ic_russia), 9, 10),
        Weather(City("Казань", 55.8304307, 49.06608060000008, R.drawable.ic_russia), 11, 12),
        Weather(City("Челябинск", 55.1644419, 61.4368432, R.drawable.ic_russia), 13, 14),
        Weather(City("Омск", 54.9884804, 73.32423610000001, R.drawable.ic_russia), 15, 16),
        Weather(City("Ростов-на-Дону", 47.2357137, 39.701505, R.drawable.ic_russia), 17, 18),
        Weather(City("Уфа", 54.7387621, 55.972055400000045, R.drawable.ic_russia), 19, 20)
    )
}
