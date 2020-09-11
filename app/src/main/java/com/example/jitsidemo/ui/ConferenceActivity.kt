package com.example.jitsidemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView

/** Alternative way of making Conference Activity using
 * JitsiMeetActivityInterface instead of direct  JitsiMeetActivity
 */

class ConferenceActivity : AppCompatActivity(), JitsiMeetActivityInterface {
    private var view: JitsiMeetView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("https://meet.jit.si/${intent.getStringExtra("roomName")}")
            .build()
        view!!.join(options)

        setContentView(view)
    }



    override fun onBackPressed() {
        JitsiMeetActivityDelegate.onBackPressed();

    }


    override fun onResume() {
        super.onResume()
        JitsiMeetActivityDelegate.onHostResume(this);
    }

    override fun onStop() {
        super.onStop()
        JitsiMeetActivityDelegate.onHostPause(this);
    }


    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        JitsiMeetActivityDelegate.requestPermissions(this,p0,p1,p2)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        JitsiMeetActivityDelegate.onNewIntent(intent);

    }

    override fun onDestroy() {
        super.onDestroy()

        view?.dispose();
        view = null;

        JitsiMeetActivityDelegate.onHostDestroy(this);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        JitsiMeetActivityDelegate.onActivityResult(
            this, requestCode, resultCode, data);
    }
}