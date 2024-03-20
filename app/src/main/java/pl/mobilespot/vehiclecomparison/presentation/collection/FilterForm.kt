package pl.mobilespot.vehiclecomparison.presentation.collection

data class FilterForm(val name: String, val manufacturer: String) {
    fun isEmpty() = name.isEmpty() && manufacturer.isEmpty()

    companion object {
        val raw = FilterForm("", "")
    }
}