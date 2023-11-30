package com.example.rates.domain

import com.example.base.architecture.domain.FlowResultUseCase
import com.example.base.common.result.Result
import com.example.rates.common.exceptions.YearOutOfBoundException
import com.example.rates.data.model.ExchangeRates
import com.example.rates.data.repository.ExchangeRatesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetExchangeRatesUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val exchangeRatesRepository: ExchangeRatesRepository,
    private val clock: Clock,
) : FlowResultUseCase<GetExchangeRatesUseCase.Params, ExchangeRates>(coroutineDispatcher) {

    override fun execute(parameters: Params): Flow<Result<ExchangeRates, Exception>> {
        val currentDate = clock.now().toLocalDateTime(TimeZone.UTC)
        val currentYear = currentDate.year
        val dates: List<String> = parameters.year?.let { yearFromParams ->
            if (yearFromParams in YEAR_LOWER_THRESHOLD..currentYear) {
                if (yearFromParams == currentYear) {
                    getFormattedDatesForCurrentYear(currentDate, currentYear)
                } else {
                    getFormattedDates(LAST_MONTH, yearFromParams)
                }
            } else {
                throw YearOutOfBoundException()
            }
        } ?: run { getFormattedDatesForCurrentYear(currentDate, currentYear) }

        return flow {
            emit(exchangeRatesRepository.getHistoricalExchangeRates(dates.first()))
        }
    }

    private fun getFormattedDatesForCurrentYear(currentDate: LocalDateTime, year: Int): List<String> {
        val currentMonthIndex = currentDate.month.ordinal + 1

        return getFormattedDates(currentMonthIndex, year)
    }

    private fun getFormattedDates(numberOfMonths: Int, year: Int): List<String> {
        val resultDates = mutableListOf<String>()
        for (monthIndex in 1..numberOfMonths) {
            val instant = LocalDateTime(year, monthIndex, 1, 10, 0)
                .toInstant(TimeZone.UTC)
            val dateFormat = SimpleDateFormat(DATE_FORMATTER, Locale.UK)
            val date = dateFormat.format(Date(instant.toEpochMilliseconds())).also {
                Timber.d("Formatted date for request: $it")
            }
            resultDates.add(date)
        }

        return resultDates
    }

    data class Params(val year: Int?)

    companion object {
        private const val YEAR_LOWER_THRESHOLD = 1999
        private const val LAST_MONTH = 12
        private const val DATE_FORMATTER = "yyyy-MM-dd"
    }
}