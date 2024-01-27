import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.profiledata.Message
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> get() = _messages

    init {
        observeMessages()
    }

    private fun observeMessages() {
        db.collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let { querySnapshot ->
                    val newMessages = mutableListOf<Message>()
                    for (change in querySnapshot.documentChanges) {
                        when (change.type) {
                            DocumentChange.Type.ADDED -> {
                                val message = change.document.toObject(Message::class.java)
                                newMessages.add(message)
                            }

                            else -> {}
                        }
                    }
                    _messages.value = newMessages
                }
            }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val timestamp = System.currentTimeMillis()
            val newMessage = Message(message, timestamp)
            db.collection("messages").add(newMessage)
        }
    }
}
