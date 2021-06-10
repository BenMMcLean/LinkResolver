package cl.benm.linkresolver

import android.content.Context

interface LinkHandler {

    fun handle(link: String, context: Context): Boolean

}

interface MatchingLinkHandler: LinkHandler {

    fun matching(link: String, context: Context): Boolean

}