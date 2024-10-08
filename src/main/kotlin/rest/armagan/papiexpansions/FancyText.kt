package rest.armagan.papiexpansions

class FancyText {
    companion object {
        private val smallCapsMap = mapOf(
            "a" to "ᴀ",
            "b" to "ʙ",
            "c" to "ᴄ",
            "d" to "ᴅ",
            "e" to "ᴇ",
            "f" to "ꜰ",
            "g" to "ɢ",
            "h" to "ʜ",
            "i" to "ɪ",
            "j" to "ᴊ",
            "k" to "ᴋ",
            "l" to "ʟ",
            "m" to "ᴍ",
            "n" to "ɴ",
            "o" to "ᴏ",
            "p" to "ᴘ",
            "q" to "ǫ",
            "r" to "ʀ",
            "s" to "ꜱ",
            "t" to "ᴛ",
            "u" to "ᴜ",
            "v" to "ᴠ",
            "w" to "ᴡ",
            "x" to "ˣ",
            "y" to "ʏ",
            "z" to "ᴢ",
        )

        private val roundMap = mapOf(
            "0" to "⓪",
            "1" to "①",
            "2" to "②",
            "3" to "③",
            "4" to "④",
            "5" to "⑤",
            "6" to "⑥",
            "7" to "⑦",
            "8" to "⑧",
            "9" to "⑨",
            "a" to "Ⓐ",
            "b" to "Ⓑ",
            "c" to "Ⓒ",
            "d" to "Ⓓ",
            "e" to "Ⓔ",
            "f" to "Ⓕ",
            "g" to "Ⓖ",
            "h" to "Ⓗ",
            "i" to "Ⓘ",
            "j" to "Ⓙ",
            "k" to "Ⓚ",
            "l" to "Ⓛ",
            "m" to "Ⓜ",
            "n" to "Ⓝ",
            "o" to "Ⓞ",
            "p" to "Ⓟ",
            "q" to "Ⓠ",
            "r" to "Ⓡ",
            "s" to "Ⓢ",
            "t" to "Ⓣ",
            "u" to "Ⓤ",
            "v" to "Ⓥ",
            "w" to "Ⓦ",
            "x" to "Ⓧ",
            "y" to "Ⓨ",
            "z" to "Ⓩ",
        )

        fun format(text: String, style: String): String {
            var result = text.lowercase()
            var map = mapOf<String, String>()

            when (style) {
                "smallCaps" -> {
                    map = smallCapsMap
                }
                "round" -> {
                    map = roundMap
                }
            }

            map.forEach { (k, v) -> result = result.replace(k, v) }

            return result
        }
    }
}