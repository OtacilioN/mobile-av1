package school.cesar.mobileav1

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FruitItem(val imageResource: Int, val fruitName: String?, val fruitDescription: String?, val imageUri: Uri? = null):
    Parcelable