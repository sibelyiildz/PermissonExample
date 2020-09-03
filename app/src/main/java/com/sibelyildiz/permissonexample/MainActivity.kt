package com.sibelyildiz.permissonexample

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera.setOnClickListener {
            cameraTask()
        }
        btnSmsAndContact.setOnClickListener {
            smsAndContactTask()
        }

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

        when (requestCode) {
            RC_CAMERA_PERM -> {
                txtCamera.text = "İstek iptal edildi"
            }
            RC_SMS_AND_CONTACT_PERM -> {
                txtSmsAndContact.text = "İstek iptal edildi"
            }
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        when (requestCode) {
            RC_CAMERA_PERM -> {
                txtCamera.text = "İzin verilmedi"
            }
            RC_SMS_AND_CONTACT_PERM -> {
                txtSmsAndContact.text = "İzin verilmedi"
            }
        }
    }

    override fun onRationaleDenied(requestCode: Int) {
        when (requestCode) {
            RC_CAMERA_PERM -> {
                txtCamera.text = "İzin verilmedi"
            }
            RC_SMS_AND_CONTACT_PERM -> {
                txtSmsAndContact.text = "İzin verilmedi"
            }
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {

        when (requestCode) {
            RC_CAMERA_PERM -> {
                txtCamera.text = "İstek onaylandı"
            }
            RC_SMS_AND_CONTACT_PERM -> {
                txtSmsAndContact.text = "İstek onaylandı"

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)

    }

    companion object {
        private const val RC_CAMERA_PERM = 123
        private const val RC_SMS_AND_CONTACT_PERM = 11
    }


    @AfterPermissionGranted(RC_SMS_AND_CONTACT_PERM)
    private fun smsAndContactTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(this, "İzin zaten verildi", Toast.LENGTH_SHORT).show()
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "Devam etmek için izin vermelisiniz.",
                    RC_SMS_AND_CONTACT_PERM,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS
            )
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    private fun cameraTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            Toast.makeText(this, "İzin zaten verildi", Toast.LENGTH_SHORT).show()
        } else {    // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    "Devam etmek için izin vermelisiniz.",
                    RC_CAMERA_PERM,
                    Manifest.permission.CAMERA)
        }
    }
}