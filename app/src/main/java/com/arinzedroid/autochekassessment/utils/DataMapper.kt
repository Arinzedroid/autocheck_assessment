package com.arinzedroid.autochekassessment.utils
import com.arinzedroid.autochekassessment.model.*
import java.util.*

object DataMapper {

    fun mapCarResponseToEntity(response: Result?, currentPage: Int?): SearchCarEntity{
         return SearchCarEntity(
             currentPage = currentPage ?: 1,
             bodyTypeId = response?.bodyTypeId ?: "",
             city = response?.city ?: "",
             depositReceived = response?.depositReceived ?: false,
             gradeScore = response?.gradeScore ?: 0.0,
             hasFinancing = response?.hasFinancing ?: false,
             hasThreeDImage = response?.hasThreeDImage ?: false,
             hasWarranty = response?.hasWarranty ?: false,
             id = response?.id ?: "0",
             imageUrl = response?.imageUrl ?: "",
             installment = response?.installment ?: 0.0,
             loanValue = response?.loanValue ?: 0.0,
             marketplaceOldPrice = response?.marketplaceOldPrice ?: 0.0,
             marketplacePrice = response?.marketplacePrice ?: 0.0,
             mileage = response?.mileage ?: 0.0,
             mileageUnit = response?.mileageUnit ?: "",
             sellingCondition = response?.sellingCondition ?: "",
             sold = response?.sold ?: false,
             state = response?.state ?: "",
             threeDImageUrl = response?.threeDImageUrl ?: "",
             title = response?.title ?: "",
             websiteUrl = response?.websiteUrl ?: "",
             year = response?.year ?: 0
         )
    }

    fun mapPopularCarEntity(response: Make?, currentPage: Int?): PopularCarEntity{
        return PopularCarEntity(
            id = response?.id ?: 0,
            imageUrl = response?.imageUrl ?: "",
            name = response?.name ?: "",
            currentPage = currentPage ?: 1
        )
    }

    fun mapMediaToEntity(response: Make?, currentPage: Int?, productId: String): MediaEntity{
        return MediaEntity(
            url = response?.imageUrl ?: "",
            type = response?.type ?: "",
            currentPage = currentPage ?: 0,
            id = response?.id ?: 0,
            carId = productId
        )
    }
}