package com.teamd.hungerexpressfooddelivery.interfaces

import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem

interface RestaurantItemClickedListener {
    fun onEditClicked(item: RestaurantListRequestItem)
    fun onDeleteClicked(item: RestaurantListRequestItem)
    fun onItemClicked(item: RestaurantListRequestItem)
}