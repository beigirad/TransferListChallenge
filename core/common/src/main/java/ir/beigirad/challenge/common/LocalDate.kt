package ir.beigirad.challenge.common

import saman.zamani.persiandate.PersianDate
import java.util.Date

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
class LocalDate internal constructor(date: Date) {
    var persianCalendar = PersianDate(date.time)

    val day: Int
        get() = persianCalendar.shDay
    val weekDayName: String
        get() = persianCalendar.dayName()
    val month: Int
        get() = persianCalendar.shMonth
    val monthName: String
        get() = persianCalendar.monthName()
    val year: Int
        get() = persianCalendar.shYear
    val hour: Int
        get() = persianCalendar.hour
    val minute: Int
        get() = persianCalendar.minute
}