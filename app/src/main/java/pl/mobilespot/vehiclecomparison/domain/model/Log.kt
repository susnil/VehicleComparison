package pl.mobilespot.vehiclecomparison.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["description"], unique = true)])
data class Log(
    val createdAt: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Parcelable