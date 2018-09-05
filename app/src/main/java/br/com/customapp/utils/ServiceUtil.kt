package br.com.customapp.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by lucas on 05/09/18.
 */
object ServiceUtil {
    fun buildRetrofit(baseUrl: String, connectTimeout: Long = 30, readTimeout: Long = 30): Retrofit {
        val client = OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build()
        val gson = GsonBuilder().serializeNulls().create()

        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun getErrorMessageFromJson(json: JsonObject): String? {
        var message: String? = null
        if (json.has("error")) {
            if (json.get("error").asJsonObject.has("message")) {
                message = json.get("error").asJsonObject.get("message").asString
            }
        } else if (json.has("reasonMessage")) {
            message = json.get("reasonMessage").asString
        }

        return message
    }

    fun handleServiceError(error: Throwable): JsonObject? {
        if (error is HttpException) {
            var errorBody = error.response().errorBody()
            if (errorBody != null) {
                var stringBody = errorBody.string()

                var jsonParser = JsonParser()
                return jsonParser.parse(stringBody).asJsonObject
            }
        }
        return null
    }

    fun getErrorMessageFromResponse(error: Throwable): String {
        var response = handleServiceError(error)
        if (response != null) {
            var errorMessage = getErrorMessageFromJson(response)
            if (errorMessage != null) {
                return errorMessage
            }
        }
        return "Erro ao se comunicar com o servidor"
    }
}