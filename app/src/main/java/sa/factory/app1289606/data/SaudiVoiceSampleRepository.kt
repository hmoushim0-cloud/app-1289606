package sa.factory.app1289606.data

import sa.factory.app1289606.model.SaudiVoiceSample
import sa.factory.app1289606.model.SampleOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface SaudiVoiceSampleRepository {
    fun getAvailableCatalog(): Flow<List<SampleOption>>
    fun getActiveRequest(): Flow<SaudiVoiceSample?>
    suspend fun submitRequest(request: SaudiVoiceSample): Result<String>
}

class InMemorySaudiVoiceSampleRepository : SaudiVoiceSampleRepository {
    private val _catalog = MutableStateFlow<List<SampleOption>>(listOf(
        SampleOption("1", "عينة صوتية قصيرة (15-30 ثانية)", "تسجيل عينة صوتية قصيرة باللهجة السعودية لتقييم الجودة قبل المشروع الكامل", 5000L, "verified"),
        SampleOption("2", "عينة صوتية متوسطة (30-60 ثانية)", "تسجيل عينة صوتية أطول لتقييم التفاصيل الصوتية", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<SaudiVoiceSample?>(null)

    override fun getAvailableCatalog(): Flow<List<SampleOption>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<SaudiVoiceSample?> = _active.asStateFlow()

    override suspend fun submitRequest(request: SaudiVoiceSample): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
