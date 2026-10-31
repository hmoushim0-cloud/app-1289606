package sa.factory.app1289606.model

import kotlinx.serialization.Serializable

@Serializable
data class SampleOption(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class SaudiVoiceSample(
    val requestId: String,
    val items: List<SampleOption>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: SaudiVoiceSampleStatus = SaudiVoiceSampleStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class SaudiVoiceSampleStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
