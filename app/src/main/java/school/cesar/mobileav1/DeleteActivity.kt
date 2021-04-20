package school.cesar.mobileav1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val fruitItem = intent.getParcelableExtra<FruitItem>(MainActivity.MAIN_ACTIVITY_EXTRA_DATA_ID)
        val position = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_EXTRA_POSITION_ID, 0)
        val imageView: ImageView = findViewById(R.id.imageView2)
        if(fruitItem?.imageUri != null){
            val imageUri: Uri = fruitItem.imageUri
            imageView.setImageURI(imageUri)
        }
        else{
            imageView.setImageResource(fruitItem?.imageResource!!)
        }
        val fruitTitle: TextView = findViewById(R.id.details_fruit_title)
        val fruitDescription: TextView = findViewById(R.id.details_fruit_description)
        fruitTitle.setText(fruitItem.fruitName)
        fruitDescription.setText(fruitItem.fruitDescription)

        val deleteBtn = findViewById<FloatingActionButton>(R.id.deleteBtn)
        deleteBtn.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_EXTRA_DATA_ID, position)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}