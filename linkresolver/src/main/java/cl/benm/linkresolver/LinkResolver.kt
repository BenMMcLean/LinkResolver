package cl.benm.linkresolver

import android.content.Context

interface LinkResolver {

    fun resolve(link: String, localContext: Context? = null)

    fun register(linkHandler: LinkHandler, order: Int = 100)
    fun deregister(linkHandler: LinkHandler)

}