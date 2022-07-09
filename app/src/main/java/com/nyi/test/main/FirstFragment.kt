package com.nyi.test.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.mediarouter.app.MediaRouteChooserDialog
import androidx.mediarouter.media.MediaControlIntent
import androidx.mediarouter.media.MediaRouteSelector
import androidx.mediarouter.media.MediaRouter
import androidx.mediarouter.media.MediaRouterParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.SimpleListViewAdapter
import com.nyi.test.databinding.FragmentFirstBinding
import com.nyi.test.model.Movie

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = listOf(
            "Matrix", "Harry Potter", "Lord of the Ring"
        )

        val data2 = listOf(
            "Matrix", "Harry Potter", "Lord of the Ring", "GOT", "Big Bang", "Silicon Valley", "Start Up",
        )

//        // recycler adapter
//        val simpleRecyclerAdapter = SimpleRecyclerViewAdapter()
//
//        binding.rvAnimal.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        binding.rvAnimal.adapter = simpleRecyclerAdapter
//
//        simpleRecyclerAdapter.addData(data)
//
//        binding.btnReload.setOnClickListener {
//            simpleRecyclerAdapter.addData(data)
//        }

         var movieData = listOf(
            Movie(1, "Matrix", false),
            Movie(2, "Harry Potter", false),
            Movie(3, "Lord of the Ring", false),
            Movie(4, "GOT", false),
            Movie(5, "Big Bang", false),
        )

        // list adapter
        val listAdapter = SimpleListViewAdapter(onItemClick = { data ->
            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        })


        binding.rvAnimal.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvAnimal.adapter = listAdapter

        listAdapter.submitList(data)

        binding.btnReload.setOnClickListener {
            listAdapter.submitList(data)
        }

        binding.rvAnimal.setItemViewCacheSize(2)


        val pool = RecyclerView.RecycledViewPool()
        binding.rvAnimal.setRecycledViewPool(pool)

        showOutputSelector()
    }

    private class MediaRouterCallback : MediaRouter.Callback() {
        override fun onRouteSelected(
            router: MediaRouter,
            route: MediaRouter.RouteInfo,
            reason: Int
        ) {
            if (reason == MediaRouter.UNSELECT_REASON_ROUTE_CHANGED) {
                Log.d("Test","Unselected because route changed, continue playback")
            } else if (reason == MediaRouter.UNSELECT_REASON_STOPPED) {
                Log.d("Test", "Unselected because route was stopped, stop playback")
            }
        }
    }
    private fun showOutputSelector() {
        val router = MediaRouter.getInstance(requireContext())
        val routeSelector = MediaRouteSelector.Builder() // Add control categories that this media app is interested in.
            .addControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO)
            .build()
        router.routerParams = MediaRouterParams.Builder().setTransferToLocalEnabled(true).build()
        router.addCallback(routeSelector, MediaRouterCallback(),
            MediaRouter.CALLBACK_FLAG_REQUEST_DISCOVERY);

        binding.btnMediaRoute.routeSelector = routeSelector
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}