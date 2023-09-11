package com.hazem.boruto.domain.model

import androidx.annotation.DrawableRes
import com.hazem.boruto.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First:OnBoardingPage(R.drawable.greetings
        ,"Greetings"
        ,"Are you a Boruto fan? Because if you are then we have a great news for you!")
    object Second:OnBoardingPage(R.drawable.explore
        ,"Explore"
        ,"Find your favorite heroes and learn some of the things that you didn't know about.")
    object Third:OnBoardingPage(R.drawable.power
        ,"Power"
        ,"Check out your hero's power and see how much are they strong comparing to others.")
}
