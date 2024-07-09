package hatsuser.ptc2024.hats

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registrar_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //1-Mandar a llamar a todos los elementos de la pantalla
        val txtcorreo = findViewById<EditText>(R.id.txtcorreo)
        val txtcontraseña = findViewById<EditText>(R.id.txtcontraseña)
        val btnregistrarse = findViewById<Button>(R.id.btniraRegistrarse)

        //Programar el Boton
         btnregistrarse.setOnClickListener {
          FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtcorreo.text.toString(), txtcontraseña.text.toString())

        }
    }
}