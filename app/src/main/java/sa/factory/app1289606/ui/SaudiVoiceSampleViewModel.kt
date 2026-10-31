package sa.factory.app1289606.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1289606.data.SaudiVoiceSampleRepository
import sa.factory.app1289606.model.SaudiVoiceSample
import sa.factory.app1289606.model.SampleOption
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SaudiVoiceSampleViewModel(
    private val repository: SaudiVoiceSampleRepository
) : ViewModel() {
    val catalog: StateFlow<List<SampleOption>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<SaudiVoiceSample?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: SaudiVoiceSample) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
