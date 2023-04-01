package com.test.androidavp.dto


import com.squareup.moshi.Json

data class RocketDetailDto(
    @field:Json(name = "active")
    val active: Boolean?,
    @field:Json(name = "boosters")
    val boosters: Int?,
    @field:Json(name = "company")
    val company: String?,
    @field:Json(name = "cost_per_launch")
    val costPerLaunch: Int?,
    @field:Json(name = "country")
    val country: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "diameter")
    val diameter: Diameter?,
    @field:Json(name = "engines")
    val engines: Engines?,
    @field:Json(name = "first_flight")
    val firstFlight: String?,
    @field:Json(name = "first_stage")
    val firstStage: FirstStage?,
    @field:Json(name = "flickr_images")
    val flickrImages: List<String>?,
    @field:Json(name = "height")
    val height: Height?,
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "landing_legs")
    val landingLegs: LandingLegs?,
    @field:Json(name = "mass")
    val mass: Mass?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "payload_weights")
    val payloadWeights: List<PayloadWeight?>?,
    @field:Json(name = "second_stage")
    val secondStage: SecondStage?,
    @field:Json(name = "stages")
    val stages: Int?,
    @field:Json(name = "success_rate_pct")
    val successRatePct: Int?,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "wikipedia")
    val wikipedia: String?
) {
    data class Diameter(
        @field:Json(name = "feet")
        val feet: Double?,
        @field:Json(name = "meters")
        val meters: Double?
    )

    data class Engines(
        @field:Json(name = "engine_loss_max")
        val engineLossMax: Int?,
        @field:Json(name = "isp")
        val isp: Isp?,
        @field:Json(name = "layout")
        val layout: String?,
        @field:Json(name = "number")
        val number: Int?,
        @field:Json(name = "propellant_1")
        val propellant1: String?,
        @field:Json(name = "propellant_2")
        val propellant2: String?,
        @field:Json(name = "thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel?,
        @field:Json(name = "thrust_to_weight")
        val thrustToWeight: Double?,
        @field:Json(name = "thrust_vacuum")
        val thrustVacuum: ThrustVacuum?,
        @field:Json(name = "type")
        val type: String?,
        @field:Json(name = "version")
        val version: String?
    ) {
        data class Isp(
            @field:Json(name = "sea_level")
            val seaLevel: Int?,
            @field:Json(name = "vacuum")
            val vacuum: Int?
        )

        data class ThrustSeaLevel(
            @field:Json(name = "kN")
            val kN: Int?,
            @field:Json(name = "lbf")
            val lbf: Int?
        )

        data class ThrustVacuum(
            @field:Json(name = "kN")
            val kN: Int?,
            @field:Json(name = "lbf")
            val lbf: Int?
        )
    }

    data class FirstStage(
        @field:Json(name = "burn_time_sec")
        val burnTimeSec: Int?,
        @field:Json(name = "engines")
        val engines: Int?,
        @field:Json(name = "fuel_amount_tons")
        val fuelAmountTons: Float?,
        @field:Json(name = "reusable")
        val reusable: Boolean?,
        @field:Json(name = "thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel?,
        @field:Json(name = "thrust_vacuum")
        val thrustVacuum: ThrustVacuum?
    ) {
        data class ThrustSeaLevel(
            @field:Json(name = "kN")
            val kN: Int?,
            @field:Json(name = "lbf")
            val lbf: Int?
        )

        data class ThrustVacuum(
            @field:Json(name = "kN")
            val kN: Int?,
            @field:Json(name = "lbf")
            val lbf: Int?
        )
    }

    data class Height(
        @field:Json(name = "feet")
        val feet: Double?,
        @field:Json(name = "meters")
        val meters: Float?
    )

    data class LandingLegs(
        @field:Json(name = "material")
        val material: String?,
        @field:Json(name = "number")
        val number: Int?
    )

    data class Mass(
        @field:Json(name = "kg")
        val kg: Int?,
        @field:Json(name = "lb")
        val lb: Int?
    )

    data class PayloadWeight(
        @field:Json(name = "id")
        val id: String?,
        @field:Json(name = "kg")
        val kg: Int?,
        @field:Json(name = "lb")
        val lb: Int?,
        @field:Json(name = "name")
        val name: String?
    )

    data class SecondStage(
        @field:Json(name = "burn_time_sec")
        val burnTimeSec: Int?,
        @field:Json(name = "engines")
        val engines: Int?,
        @field:Json(name = "fuel_amount_tons")
        val fuelAmountTons: Double?,
        @field:Json(name = "payloads")
        val payloads: Payloads?,
        @field:Json(name = "reusable")
        val reusable: Boolean?,
        @field:Json(name = "thrust")
        val thrust: Thrust?
    ) {
        data class Payloads(
            @field:Json(name = "composite_fairing")
            val compositeFairing: CompositeFairing?,
            @field:Json(name = "option_1")
            val option1: String?
        ) {
            data class CompositeFairing(
                @field:Json(name = "diameter")
                val diameter: Diameter?,
                @field:Json(name = "height")
                val height: Height?
            ) {
                data class Diameter(
                    @field:Json(name = "feet")
                    val feet: Double?,
                    @field:Json(name = "meters")
                    val meters: Double?
                )

                data class Height(
                    @field:Json(name = "feet")
                    val feet: Double?,
                    @field:Json(name = "meters")
                    val meters: Double?
                )
            }
        }

        data class Thrust(
            @field:Json(name = "kN")
            val kN: Int?,
            @field:Json(name = "lbf")
            val lbf: Int?
        )
    }
}