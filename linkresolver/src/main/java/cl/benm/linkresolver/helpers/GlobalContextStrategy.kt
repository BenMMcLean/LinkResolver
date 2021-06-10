package cl.benm.linkresolver.helpers

import android.content.Context

interface GlobalContextStrategy {

    /**
     * Gets an application context instance
     */
    fun fetchGlobalContext(): Context

}