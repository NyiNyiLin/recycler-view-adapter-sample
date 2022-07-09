package com.nyi.test.main

import android.content.Context
import android.media.AudioDeviceInfo
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.mediarouter.app.MediaRouteChooserDialog
import com.nyi.test.R
import com.nyi.test.databinding.ActivityMainBinding
import kotlin.math.log
import android.media.AudioManager
import android.content.Intent
import androidx.mediarouter.media.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                showOutputSelector()
                //showAudioSelection()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private var remotePlaybackClient: RemotePlaybackClient? = null
    private class MediaRouterCallback : MediaRouter.Callback() {
        override fun onRouteSelected(
            router: MediaRouter,
            route: MediaRouter.RouteInfo,
            reason: Int
        ) {
            Log.d("Test","onRouteSelected")
            if (reason == MediaRouter.UNSELECT_REASON_ROUTE_CHANGED) {
                Log.d("Test","Unselected because route changed, continue playback")
            } else if (reason == MediaRouter.UNSELECT_REASON_STOPPED) {
                Log.d("Test", "Unselected because route was stopped, stop playback")
            }
        }

        override fun onRouteAdded(router: MediaRouter?, route: MediaRouter.RouteInfo?) {
            super.onRouteAdded(router, route)
            Log.d("Test","onRouteAdded")
        }

        override fun onRouteChanged(router: MediaRouter?, route: MediaRouter.RouteInfo?) {
            super.onRouteChanged(router, route)
            Log.d("Test","onRouteChanged ${route?.name}")
        }

        override fun onRouteRemoved(router: MediaRouter?, route: MediaRouter.RouteInfo?) {
            super.onRouteRemoved(router, route)
            Log.d("Test","onRouteRemoved")

        }
    }

    private fun showOutputMediaSelector() {
        val intent = Intent("com.android.settings.panel.action.MEDIA_OUTPUT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun showOutputSelector() {

        val router = MediaRouter.getInstance(applicationContext)
        //router.routerParams = MediaRouterParams.Builder().setTransferToLocalEnabled(true).build()

        val routeSelector = MediaRouteSelector.Builder() // Add control categories that this media app is interested in.
            .addControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO)
            .addControlCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK)
            .build()
        router.addCallback(routeSelector, MediaRouterCallback(),
            MediaRouter.CALLBACK_FLAG_PERFORM_ACTIVE_SCAN);

        router.providers.forEach { provider ->
            provider.routes.forEach {
                Log.d("Test", "${provider.componentName} ${it.name}")
            }

        }
        val dialog = MediaRouteChooserDialog(this, R.style.Theme_AppCompat_NoActionBar)
        dialog.routeSelector = routeSelector
        dialog.refreshRoutes()
        //dialog.setTitle("Test")
        dialog.show()
    }

    private fun showAudioSelection() {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
        val list = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
        for(deviceInfo in list) {
            Log.d("Test", "${deviceInfo.type}")
        }
        Log.d("Test", "TYPE_WIRED_HEADPHONES ${AudioDeviceInfo.TYPE_WIRED_HEADPHONES}")
        Log.d("Test", "TYPE_WIRED_HEADPHONES ${AudioDeviceInfo.TYPE_WIRED_HEADPHONES}")

        //for speakerphone on
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);

        //for headphone on
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(false);

        /*
        Use audioManager.setMode(AudioManager.MODE_IN_CALL); & audioManager.setSpeakerphoneOn(false); to use front speaker/earpiece. But this would play audio in earpiece not on speaker.

        To use rear speaker, use audioManager.setMode(AudioManager.MODE_NORMAL); & audioManager.setSpeakerphoneOn(true);

        If accessory connected; Use audioManager.setMode(AudioManager.MODE_IN_CALL); & audioManager.setSpeakerphoneOn(false); to use front speaker/earpiece. But this would play audio in earpiece not on speaker.
        To use rear speaker, use audioManager.setMode(AudioManager.MODE_IN_CALL); & audioManager.setSpeakerphoneOn(true);

        Note: Make sure audioManager.setWiredHeadsetOn(boolean on) and audioManager.setBluetoothScoOn(boolean on) set to false to route audio via earpiece . And set either to true to route audio accordingly.
         */
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}