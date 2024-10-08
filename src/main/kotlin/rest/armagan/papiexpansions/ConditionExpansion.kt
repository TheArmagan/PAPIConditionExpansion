package rest.armagan.papiexpansions
import me.clip.placeholderapi.PlaceholderAPI
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

class ConditionExpansion : PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return "condition"
    }

    override fun getAuthor(): String {
        return "TheArmagan"
    }

    override fun getVersion(): String {
        return "0.0.1"
    }

    override fun onPlaceholderRequest(player: Player?, identifier: String): String {
        val replaced = PlaceholderAPI.setBracketPlaceholders(player, identifier)
        val parsed = parseIdentifier(replaced)

        val condition = getValue(parsed, "if")
        val thenRes = getValue(parsed, "then")
        val elseRes = getValue(parsed, "else")

        val a = getValue(parsed, "a")
        val b = getValue(parsed, "b")

        if (condition.isEmpty()) return thenRes;
        when (condition) {
            "==" -> {
                return if (a == b) thenRes else elseRes
            }
            "!=" -> {
                return if (a != b) thenRes else elseRes
            }
            ">" -> {
                return if (a.toDouble() > b.toDouble()) thenRes else elseRes
            }
            ">=" -> {
                return if (a.toDouble() >= b.toDouble()) thenRes else elseRes
            }
            "<" -> {
                return if (a.toDouble() < b.toDouble()) thenRes else elseRes
            }
            "<=" -> {
                return if (a.toDouble() <= b.toDouble()) thenRes else elseRes
            }
            "&&" -> {
                return if (a.toBoolean() && b.toBoolean()) thenRes else elseRes
            }
            "||" -> {
                return if (a.toBoolean() || b.toBoolean()) thenRes else elseRes
            }
            "!" -> {
                return if (!a.toBoolean()) thenRes else elseRes
            }
            "contains" -> {
                return if (a.contains(b)) thenRes else elseRes
            }
            "notContains" -> {
                return if (!a.contains(b)) thenRes else elseRes
            }
            "startsWith" -> {
                return if (a.startsWith(b)) thenRes else elseRes
            }
            "notStartsWith" -> {
                return if (!a.startsWith(b)) thenRes else elseRes
            }
            "endsWith" -> {
                return if (a.endsWith(b)) thenRes else elseRes
            }
            "notEndsWith" -> {
                return if (!a.endsWith(b)) thenRes else elseRes
            }
            "matches" -> {
                return if (a.matches(b.toRegex())) thenRes else elseRes
            }
            "notMatches" -> {
                return if (!a.matches(b.toRegex())) thenRes else elseRes
            }
            "empty" -> {
                return if (a.isEmpty()) thenRes else elseRes
            }
            "notEmpty" -> {
                return if (a.isNotEmpty()) thenRes else elseRes
            }
            "between" -> {
                val c = getValue(parsed, "c");
                return if (a.toDouble() >= b.toDouble() && a.toDouble() <= c.toDouble()) thenRes else elseRes
            }
            "notBetween" -> {
                val c = getValue(parsed, "c");
                return if (a.toDouble() < b.toDouble() || a.toDouble() > c.toDouble()) thenRes else elseRes
            }
            "in" -> {
                return if (b.split(",").contains(a)) thenRes else elseRes
            }
            "notIn" -> {
                return if (!b.split(",").contains(a)) thenRes else elseRes
            }
            "longer" -> {
                return if (a.length > b.length) thenRes else elseRes
            }
            "shorter" -> {
                return if (a.length < b.length) thenRes else elseRes
            }
            "isInteger" -> {
                return if (a.matches(Regex("[0-9]+")) && a.toInt().toString() == a) thenRes else elseRes
            }
            "isNotInteger" -> {
                return if (!a.matches(Regex("[0-9]+")) || a.toInt().toString() != a) thenRes else elseRes
            }
            "isDouble" -> {
                return if (a.matches(Regex("[0-9]+")) || a.matches(Regex("[0-9]+.[0-9]+"))) thenRes else elseRes
            }
            "isNotDouble" -> {
                return if (!a.matches(Regex("[0-9]+")) && !a.matches(Regex("[0-9]+.[0-9]+"))) thenRes else elseRes
            }
            "isBoolean" -> {
                return if (a == "true" || a == "false") thenRes else elseRes
            }
            "isNotBoolean" -> {
                return if (a != "true" && a != "false") thenRes else elseRes
            }
            "isAlphabetic" -> {
                return if (a.matches(Regex("[a-zA-Z]+"))) thenRes else elseRes
            }
            "isNotAlphabetic" -> {
                return if (!a.matches(Regex("[a-zA-Z]+"))) thenRes else elseRes
            }
            "isAlphanumeric" -> {
                return if (a.matches(Regex("[a-zA-Z0-9]+"))) thenRes else elseRes
            }
            "isNotAlphanumeric" -> {
                return if (!a.matches(Regex("[a-zA-Z0-9]+"))) thenRes else elseRes
            }
        }

        return ""
    }

    private fun getValue(values: Map<String, String>, key: String): String {
        var value = values[key] ?: return ""
        val options = (getValue(values, "${key}!options") ?: "").split(",")

        options.forEach { option ->
            when (option) {
                "lowercase" -> value = value.lowercase()
                "uppercase" -> value = value.uppercase()
                "capitalize" -> value = value.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                "reverse" -> value = value.reversed()
                "trim" -> value = value.trim()
                "trimStart" -> value = value.trimStart()
                "trimEnd" -> value = value.trimEnd()
                "replace" -> {
                    val a = getValue(values, "${key}!replaceFrom")
                    val b = getValue(values, "${key}!replaceTo")
                    value = value.replace(a, b)
                }
                "stripColors" -> value = ChatColor.stripColor(value).toString()
                "substring" -> {
                    var a = getValue(values, "${key}!substringStart").toInt()
                    if (a < 0) a = value.length - a;
                    var b = getValue(values, "${key}!substringEnd").toInt()
                    if (b < 0) b = value.length - b;
                    value = value.substring(a, b)
                }
                "join" -> {
                    val a = getValue(values, "${key}!joinSplitBy")
                    val b = getValue(values, "${key}!joinWith")
                    value = value.split(a).joinToString(b)
                }
                "length" -> value = value.length.toString()
                "toInt" -> value = value.toInt().toString()
                "toDouble" -> value = value.toDouble().toString()
                "toBoolean" -> value = value.toBoolean().toString()
                "onlyNumbers" -> value = value.replace(Regex("[^0-9.,]"), "")
                "onlyLetters" -> value = value.replace(Regex("[^a-zA-Z]"), "")
                "onlyAlphanumeric" -> value = value.replace(Regex("[^a-zA-Z0-9]"), "")
                "fancyText" -> {
                    val formatName = getValue(values, "${key}!fancyFormat")
                    value = FancyText.format(value, formatName)
                }
                "repeat" -> {
                    val a = getValue(values, "${key}!repeatTimes")
                    value = value.repeat(a.toInt())
                }
            }
        }

        return value;
    }

    private fun parseIdentifier(identifier: String): Map<String, String> {
        val parts = identifier.split("_")
        val map = mutableMapOf<String, String>()
        for (part in parts) {
            val key = part.substring(0, part.indexOf(":"))
            val value = part.substring(part.indexOf(":") + 1)
            map[key] = value
        }
        return map
    }
}