package com.arinzedroid.autochekassessment.model
import com.google.gson.annotations.SerializedName


data class CarDetailResponse(
    @SerializedName("bodyType") val bodyType: BodyType?,
    @SerializedName("carFeatures") val carFeatures: List<Any?>?,
    @SerializedName("city") val city: String?,
    val title: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("damageMedia") val damageMedia: List<DamageMedia?>?,
    @SerializedName("depositReceived") val depositReceived: Boolean?,
    @SerializedName("engineType") val engineType: String?,
    @SerializedName("exteriorColor") val exteriorColor: String?,
    @SerializedName("features") val features: List<Any?>?,
    @SerializedName("fuelType") val fuelType: String?,
    @SerializedName("gradeScore") val gradeScore: Double?,
    @SerializedName("hasFinancing") val hasFinancing: Boolean?,
    @SerializedName("hasThreeDImage") val hasThreeDImage: Boolean?,
    @SerializedName("hasWarranty") val hasWarranty: Boolean?,
    @SerializedName("id") val id: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("installment") val installment: Double?,
    @SerializedName("insured") val insured: Boolean?,
    @SerializedName("interiorColor") val interiorColor: String?,
    @SerializedName("isFeatured") val isFeatured: Boolean?,
    @SerializedName("loanValue") val loanValue: Double?,
    @SerializedName("marketplaceOldPrice") val marketplaceOldPrice: Double?,
    @SerializedName("marketplacePrice") val marketplacePrice: Double?,
    @SerializedName("marketplaceVisible") val marketplaceVisible: Boolean?,
    @SerializedName("marketplaceVisibleDate") val marketplaceVisibleDate: String?,
    @SerializedName("mileage") val mileage: Double?,
    @SerializedName("mileageUnit") val mileageUnit: String?,
    @SerializedName("model") val model: Model?,
    @SerializedName("modelFeatures") val modelFeatures: List<Any?>?,
    @SerializedName("ownerType") val ownerType: String?,
    @SerializedName("sellingCondition") val sellingCondition: String?,
    @SerializedName("sold") val sold: Boolean?,
    @SerializedName("state") val state: String?,
    @SerializedName("transmission") val transmission: String?,
    @SerializedName("updatedAt") val updatedAt: String?,
    @SerializedName("vin") val vin: String?,
    @SerializedName("websiteUrl") val websiteUrl: String?,
    @SerializedName("year") val year: Int?
)

data class BodyType(
    @SerializedName("id") val id: Int?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("name") val name: String?
)
data class DamageMedia(
    @SerializedName("inspectionItems") val inspectionItems: List<InspectionItem?>?,
    @SerializedName("name") val name: String?
)
data class InspectionItem(
    @SerializedName("comment") val comment: String?,
    @SerializedName("condition") val condition: String?,
    @SerializedName("medias") val medias: List<Media?>?,
    @SerializedName("name") val name: String?,
    @SerializedName("response") val response: String?
)
data class Media(
    @SerializedName("mediaType") val mediaType: String?,
    @SerializedName("url") val url: String?
)
data class Model(
    @SerializedName("id") val id: Int?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("make") val make: Make?,
    @SerializedName("modelFeatures") val modelFeatures: List<Any?>?,
    @SerializedName("name") val name: String?,
    @SerializedName("popular") val popular: Boolean?,
    @SerializedName("wheelType") val wheelType: String?
)

data class Make(
    @SerializedName("id") val id: Int?,
    @SerializedName("imageUrl", alternate = ["url"]) val imageUrl: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("type") val type: String?
)

data class PaginatedResponse<T>(
    @SerializedName("pagination") val pagination: Pagination?,
    @SerializedName("result", alternate = ["makeList", "carMediaList"]) val result: List<T?>?
)

data class Pagination(
    @SerializedName("currentPage") val currentPage: Int?,
    @SerializedName("pageSize") val pageSize: Int?,
    @SerializedName("total") val total: Int?
)

data class Stats(
    @SerializedName("appViewCount") val appViewCount: Int?,
    @SerializedName("appViewerCount") val appViewerCount: Int?,
    @SerializedName("interestCount") val interestCount: Int?,
    @SerializedName("processedLoanCount") val processedLoanCount: Int?,
    @SerializedName("testDriveCount") val testDriveCount: Int?,
    @SerializedName("webViewCount") val webViewCount: Int?,
    @SerializedName("webViewerCount") val webViewerCount: Int?
)

data class Result(
    @SerializedName("bodyTypeId") val bodyTypeId: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("depositReceived") val depositReceived: Boolean?,
    @SerializedName("gradeScore") val gradeScore: Double?,
    @SerializedName("hasFinancing") val hasFinancing: Boolean?,
    @SerializedName("hasThreeDImage") val hasThreeDImage: Boolean?,
    @SerializedName("hasWarranty") val hasWarranty: Boolean?,
    @SerializedName("id") val id: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("installment") val installment: Double?,
    @SerializedName("loanValue") val loanValue: Double?,
    @SerializedName("marketplaceOldPrice") val marketplaceOldPrice: Double?,
    @SerializedName("marketplacePrice") val marketplacePrice: Double?,
    @SerializedName("mileage") val mileage: Double?,
    @SerializedName("mileageUnit") val mileageUnit: String?,
    @SerializedName("sellingCondition") val sellingCondition: String?,
    @SerializedName("sold") val sold: Boolean?,
    @SerializedName("state") val state: String?,
    @SerializedName("stats") val stats: Stats?,
    @SerializedName("threeDImageUrl") val threeDImageUrl: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("websiteUrl") val websiteUrl: String?,
    @SerializedName("year") val year: Int?
)

data class ErrorModel(
    @SerializedName("message", alternate = ["error"]) val message: String?,
    @SerializedName("status", alternate = ["success"]) val status: String?
)