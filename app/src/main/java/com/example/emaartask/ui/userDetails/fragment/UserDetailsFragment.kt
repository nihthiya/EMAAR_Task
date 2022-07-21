package com.example.emaartask.ui.userDetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.emaartask.R
import com.example.emaartask.databinding.FragmentUserDetailsBinding
import com.example.emaartask.ui.userDetails.activity.UserDetailsActivity
import com.example.emaartask.ui.userDetails.viewModel.UserDetailsViewModel
import com.example.emaartask.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private lateinit var userDetailsBinding: FragmentUserDetailsBinding
    private val userDetailsViewModel: UserDetailsViewModel by viewModels()
    var currentUserID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false)
        return userDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        userDetailsBinding.lifecycleOwner = this
        userDetailsBinding.userDetailsViewModel = userDetailsViewModel
        currentUserID = requireActivity().intent.getStringExtra("userId")!!

        userDetailsViewModel.getUserByID(currentUserID)


        userDetailsViewModel.userData.observe(this.viewLifecycleOwner) { user ->
            Glide.with(requireContext()).load(user.avatarLarge).into(userDetailsBinding.ivAvatar)
            Utils.initToolbarWithTitle(
                requireActivity() as UserDetailsActivity,
                userDetailsBinding.topAppBar,
                userDetailsBinding.tvToolbarTitle,
                user.name
                )
            userDetailsBinding.tvEmail.text = "Email: " + user.email
            userDetailsBinding.tvDate.text = "Date Joined: " + Utils.formatDate(user.date)
            userDetailsBinding.tvAge.text = user.age.toString()
            userDetailsBinding.tvDOB.text = "DOB: " + Utils.formatDate(user.dob)
            userDetailsBinding.tvCity.text = "City: " + user.city
            userDetailsBinding.tvState.text = "State: " + user.state
            userDetailsBinding.tvCountry.text = "Country: " + user.country
            userDetailsBinding.tvPostcode.text = "Postcode: " + user.postcode
        }
    }

}