package com.example.feature.account.data.model.dto

import com.example.base.network.response.BaseResponse
import com.example.feature.account.data.model.AccountData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetUsageResponse(
    @SerialName("data") val data: DataDto?,
) : BaseResponse() {

    fun toAccountData(): AccountData? {
        return data?.let {
            AccountData(
                appId = data.appId,
                status = data.status,
                planName = data.plan.name,
                planQuota = data.plan.quota,
                requests = data.usage.requests,
                requestsQuota = data.usage.requestsQuota,
                requestsRemaining = data.usage.requestsRemaining,
                dailyAverageUsage = data.usage.dailyAverage,
            )
        }
    }
}

@Serializable
class DataDto(
    @SerialName("app_id") val appId: String,
    @SerialName("status") val status: String,
    @SerialName("plan") val plan: PlanDto,
    @SerialName("usage") val usage: UsageDto,
)

@Serializable
class PlanDto(
    @SerialName("name") val name: String,
    @SerialName("quota") val quota: String,
    @SerialName("update_frequency") val updateFrequency: String,
    @SerialName("features") val features: FeaturesDto,
)

@Serializable
class FeaturesDto(
    @SerialName("base") val base: Boolean,
    @SerialName("symbols") val symbols: Boolean,
    @SerialName("experimental") val experimental: Boolean,
    @SerialName("time-series") val timeSeries: Boolean,
    @SerialName("convert") val convert: Boolean,
)

@Serializable
class UsageDto(
    @SerialName("requests") val requests: Long,
    @SerialName("requests_quota") val requestsQuota: Long,
    @SerialName("requests_remaining") val requestsRemaining: Long,
    @SerialName("days_elapsed") val daysElapsed: Long,
    @SerialName("days_remaining") val daysRemaining: Long,
    @SerialName("daily_average") val dailyAverage: Long,
)