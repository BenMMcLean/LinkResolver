package cl.benm.linkresolver.handlers

import android.content.Context
import cl.benm.androidlogger.Logger
import cl.benm.linkresolver.LinkHandler

class LoggerLinkHandler(
    private val logger: Logger
): LinkHandler {

    companion object {
        const val TAG = "LinkHandler"
    }

    override fun handle(link: String, context: Context): Boolean {
        logger.d("LinkHandler", "Opening link $link")
        return true
    }

}