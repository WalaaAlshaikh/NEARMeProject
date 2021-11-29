package com.example.nearme.views.main

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.nearme.R
import com.example.nearme.adaptersimport.ImageAdapter
import com.example.nearme.model.images.Photo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng

private const val TAG = "imageFragment"
class imageFragment : Fragment() {
    private lateinit var fused: FusedLocationProviderClient



    private val imageViewModel:ImageViewModel by activityViewModels()

    private val list= mutableListOf<Photo>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if(arguments!= null)
        {
            imageViewModel.lat=requireArguments().getDouble("Lat")
            imageViewModel.long= requireArguments().getDouble("Long")
        }


        return inflater.inflate(R.layout.fragment_image, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerView1)
        val adapter= ImageAdapter(imageViewModel)
        recyclerView.adapter=adapter

        imageViewModel.callImage(imageViewModel.lat,imageViewModel.long)
        imageViewModel.mapcall(imageViewModel.lat,imageViewModel.long)


        imageViewModel.imageLiveData.observe(viewLifecycleOwner,{
            Log.d(TAG, "Observer: $it")
            Log.d("Blah",imageViewModel.lat.toString())
            Log.d("Blah",imageViewModel.long.toString())
            adapter.submittedList(it.photos.photo)

        })

        

    }

}