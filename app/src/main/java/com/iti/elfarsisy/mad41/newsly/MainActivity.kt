package com.iti.elfarsisy.mad41.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iti.elfarsisy.mad41.newsly.databinding.ActivityMainBinding
import com.iti.elfarsisy.mad41.newsly.features.login.view.SignInFragment
import com.iti.elfarsisy.mad41.newsly.features.signUp.view.SignUpFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate main layout
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        // Display signUp fragment
        displaySignInFragment()


    }


    private fun displaySignInFragment() {

        val signInFragment = SignInFragment()
        val mgr = supportFragmentManager
        val transaction = mgr.beginTransaction()
        transaction.add(R.id.homeFragmentContainer, signInFragment, "signUp")
        transaction.commit()
    }


}