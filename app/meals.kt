
import com.google.gson.annotations.SerializedName

data class meals(
    @SerializedName("menu")
    val menu: List<Menu?>? = null
)