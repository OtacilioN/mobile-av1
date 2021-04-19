package school.cesar.mobileav1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_ACTIVITY_REGISTRY_RESULT_CODE = 1
        const val MAIN_ACTIVITY_EXTRA_DATA_ID = "MAIN_ACTIVITY_EXTRA_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fruitList = initFruitList()

        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val registerFruitActivity = Intent(this, RegisterFruitActivity::class.java)
            startActivityForResult(registerFruitActivity, MAIN_ACTIVITY_REGISTRY_RESULT_CODE)
        }

        recycler_view.adapter = FruitAdapter(fruitList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (MAIN_ACTIVITY_REGISTRY_RESULT_CODE == requestCode) {
                val result = data?.getStringExtra(MAIN_ACTIVITY_EXTRA_DATA_ID)
                val toast = Toast.makeText(this, "retornou $result", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }

    private fun initFruitList(): List<FruitItem> {
        val list = ArrayList<FruitItem>()
        val morango = FruitItem(
            R.drawable.morango,
            fruitName = "Morango",
            fruitDescription = "fruta rica em antioxidantes, como antocianinas e o ácido elágico, que conferem outros benefícios para a saúde."
        )

        val pinha = FruitItem(
            R.drawable.ic_baseline_filter_vintage_24,
            fruitName = "Pinha",
            fruitDescription = "fruta rica em nutrientes como fibras, vitaminas B e C e minerais, com propriedades antioxidantes e anti-inflamatórias que ajudam a combater a dor e inflamação, aumentar as defesas do organismo e controlar os níveis de açúcar no sangue."
        )

        list += morango
        list += pinha

        return list
    }
}