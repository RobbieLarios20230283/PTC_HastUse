package hatsuser.ptc2024.hats

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //TODO ---------------------------EVento ir a Activity Registrar------------------------------------------------------
        val btniraRegistrarse = findViewById<Button>(R.id.btniraRegistrarse)

        btniraRegistrarse.setOnClickListener {
            val activity_registrar = Intent(this, RegistrarActivity::class.java)
            startActivity(activity_registrar)
        }

        //todo------------------GOOGLE ANALYTICS---------------------------------------------------------

        val Analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        Analytics.logEvent("InitScreen", bundle)

        //TODO-------------------------BOTON ACCEDER-------------------------------------------------------


        val btnacceder = findViewById<Button>(R.id.btnacceder)
        val txtemail = findViewById<EditText>(R.id.txtemail)
        val txtpassword = findViewById<EditText>(R.id.txtpassword)

        btnacceder.setOnClickListener {
                val correo = txtemail.text.toString().trim()
                val password = txtpassword.text.toString()
                if (correo.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show()
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        correo,
                        password
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val activity_menu = Intent(this, homeActivity::class.java)
                            startActivity(activity_menu)
                        } else {
                            Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()

                        }
                    }
                }
        }


    }

}


