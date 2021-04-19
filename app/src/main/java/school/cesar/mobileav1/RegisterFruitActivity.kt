package school.cesar.mobileav1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegisterFruitActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_fruit)

        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            finish()
        }

        val addImageBtn = findViewById<Button>(R.id.add_img)
        addImageBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        imageView = findViewById(R.id.imageView)

        val confirmBtn = findViewById<Button>(R.id.confirmBtn)
        confirmBtn.setOnClickListener{
            val returnIntent = Intent()
            val fruitNameInput = findViewById<EditText>(R.id.fruit_name_input)
            val valor = fruitNameInput.text.toString();
            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_EXTRA_DATA_ID, valor)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}