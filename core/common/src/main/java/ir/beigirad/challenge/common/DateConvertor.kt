package ir.beigirad.challenge.common

import java.util.Date

/**
 * Created by Farhad Beigirad on 6/23/23.
 */

object DateConvertor {
    fun convert(date: Date): LocalDate = LocalDate(date)
}