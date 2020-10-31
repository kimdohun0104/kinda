package dohun.kim.kinda.count

import kotlinx.coroutines.delay

interface CountRepository {

    suspend fun getCount(): Int
}

class DefaultCountRepository : CountRepository {

    override suspend fun getCount(): Int {
        delay(3000)
        return 13
    }
}