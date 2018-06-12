package com.android.echo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast
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
            ActivityCompat.requestPermissions(this@SplashActivity,permissionsString,131)

        }else{
            handlerForIntent()
        }

    }

    fun handlerForIntent(){
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            this.finish()
        },1000)
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


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            131->{
                if (permissions.isNotEmpty() &&grantResults[0] == PackageManager.PERMISSION_GRANTED
                        &&grantResults[1] == PackageManager.PERMISSION_GRANTED
                        &&grantResults[2] == PackageManager.PERMISSION_GRANTED
                        &&grantResults[3] == PackageManager.PERMISSION_GRANTED
                        &&grantResults[4] == PackageManager.PERMISSION_GRANTED){

                    handlerForIntent()


                }else{
                    Toast.makeText(this@SplashActivity,"Please grant all permission",Toast.LENGTH_SHORT)
                    this.finish()

                }
                return

            }
            else -> {
                Toast.makeText(this@SplashActivity,"Something went wrong",Toast.LENGTH_SHORT)
                this.finish()
                return
            }
        }

    }
}
