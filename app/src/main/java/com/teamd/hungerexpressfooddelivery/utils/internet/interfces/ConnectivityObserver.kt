package com.teamd.hungerexpressfooddelivery.utils.internet.interfces

import kotlinx.coroutines.flow.Flow

// https://www.youtube.com/watch?v=sKnVPtIZCww&list=PLzZEuVaFb9EygSJmbjX3sJAwlEzhHaPcH&index=3
// https://github.com/mohammednawas8/EasyFood
// https://www.youtube.com/playlist?list=PLKETiCsEsH0rGpSDazN5Zv053FShtmb1K
// https://www.youtube.com/watch?v=135FpDGJeEQ
// Kotlin Android Tutorial - Build Login App with NodeJS and MongoDB
    // https://www.youtube.com/watch?v=Q
// https://www.youtube.com/playlist?list=PLVRD5onwuyGAH2ZUquy5OkexxByzGUZ0G

interface ConnectivityObserver {

    fun observe(): Flow<InternetStatus>
    enum class InternetStatus {
        Available,
        Unavailable,
        Losing,
        Lost
    }
}

