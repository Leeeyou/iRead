package com.leeeyou.util

object HtmlUtils {
    fun structHtml(oriStr: String, cssList: List<String>): String {
        val htmlString = StringBuilder("<html><head>")
        for (css in cssList) {
            htmlString.append(structCssLink(css))
        }
        htmlString.append("</head><body>")
        htmlString.append("<style>img{max-width:340px !important;}</style>")
        htmlString.append(oriStr)
        htmlString.append("</body></html>")
        return htmlString.toString()
    }

    fun structCssLink(css: String): String {
        return "<link type=\\\"text/css\\\" rel=\\\"stylesheet\\\" href=\\\"$css\">"
    }

    fun translation(content: String): String {
        return content.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&mdash;", "—")
                .replace("&copy;", "©")
    }

}
