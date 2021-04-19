package school.cesar.mobileav1

import android.net.Uri

data class FruitItem(val imageResource: Int, val fruitName: String, val fruitDescription: String, val imageUri: Uri? = null)