package com.example.instareport.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.example.instareport.R




class RegMainFragment : Fragment() {
    private lateinit var viewFr:View
    private lateinit var ageDialog: Dialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        viewFr = inflater.inflate(R.layout.fragment_reg_main, container, false)

        return viewFr
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}