package com.example.emaartask.ui.userDetails.fragment

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
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
            userDetailsBinding.tvEmail.text = resources.getString(R.string.email) + user.email
            userDetailsBinding.tvDate.text = resources.getString(R.string.date) + Utils.formatDate(user.date)
            userDetailsBinding.tvAge.text = user.age.toString()
            userDetailsBinding.tvDOB.text = resources.getString(R.string.dob) + Utils.formatDate(user.dob)
            userDetailsBinding.tvCity.text = resources.getString(R.string.city)+ user.city
            userDetailsBinding.tvState.text = resources.getString(R.string.state) + user.state
            userDetailsBinding.tvCountry.text = resources.getString(R.string.user_country) + user.country
            userDetailsBinding.tvPostcode.text = resources.getString(R.string.postcode) + user.postcode
        }
    }

}