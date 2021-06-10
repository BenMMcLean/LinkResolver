package cl.benm.linkresolver.handlers

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import cl.benm.linkresolver.LinkHandler

class ActivityLinkHandler: LinkHandler {

    override fun handle(link: String, context: Context): Boolean {
        val intent = Intent(if (link.startsWith("tel:")) Intent.ACTION_DIAL else Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)

        if (context is Application) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } else {
            context.startActivity(intent)
        }

        return false
    }

}