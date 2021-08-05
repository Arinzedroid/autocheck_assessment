package com.arinzedroid.autochekassessment.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
class SearchCarEntity(
    override val currentPage: Int,
    val bodyTypeId: String,
    val city: String,
    val depositReceived: Boolean,
    val gradeScore: Double,
    val hasFinancing: Boolean,
    val hasThreeDImage: Boolean,
    val hasWarranty: Boolean,
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val installment: Double,
    val loanValue: Double,
    val marketplaceOldPrice: Double,
    val marketplacePrice: Double,
    val mileage: Double,
    val mileageUnit: String,
    val sellingCondition: String,
    val sold: Boolean,
    val state: String,
    val threeDImageUrl: String,
    val title: String,
    val websiteUrl: String,
    val year: Int
): Page{

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + websiteUrl.hashCode()
        return result
    }
}

@Entity
class PopularCarEntity(
    override val currentPage: Int,
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val name: String
): Page{
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = currentPage
        result = 31 * result + id
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}

@Entity
class StatsEntity(
    val appViewCount: Int,
    val appViewerCount: Int,
    val interestCount: Int,
    val processedLoanCount: Int,
    val testDriveCount: Int,
    val webViewCount: Int,
    val webViewerCount: Int,
    @PrimaryKey
    val carId: String,
)

class CarAndStat(
    @Embedded
    val stats: StatsEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "carId"
    )

    val cars: SearchCarEntity


)

@Entity
class MediaEntity(
    override val currentPage: Int,
    val url: String,
    val type: String,
    @PrimaryKey
    val id: Int,
    val carId: String
): Page{
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

//    override fun hashCode(): Int {
//        var result = url.hashCode()
//        result = 31 * result + id
//        result = 31 * result + carId.hashCode()
//        return result
//    }
}

interface Page{
    val currentPage: Int
}