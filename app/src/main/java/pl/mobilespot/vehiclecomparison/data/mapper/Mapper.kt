package pl.mobilespot.vehiclecomparison.data.mapper

interface Mapper<E, M> {

    fun fromDto(dto: E): M

}