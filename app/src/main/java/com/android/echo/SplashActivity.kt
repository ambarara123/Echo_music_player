package com.android.echo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.jar.Manifest

class SplashActivity : AppCompatActivity() {

    var permissionsString = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.PROCESS_OUTGOING_CALLS,
            android.Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        if (!hasPermission(this@SplashActivity,*permissionsString)){
            //we will get permission
        }else{
            Handler().postDelayed({
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                this.finish()
            },1000)
        }

    }
    //to check that the permissions are granted or not
    fun hasPermission(context: Context, vararg permissions :String): Boolean{
        var hasAllPermission = true

        for (permission in permissions){
            val res = context.checkCallingOrSelfPermission(permission)

            if (res != PackageManager.PERMISSION_GRANTED){
                hasAllPermission = false
            }
        }

        return hasAllPermission
    }
}
