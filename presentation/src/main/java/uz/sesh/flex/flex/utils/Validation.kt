package uz.sesh.flex.flex.utils

class Validation{
    companion object {
        fun validatePhoneNumber(phoneNumber: String): String {
            return phoneNumber.replace("+","")
        }
    }
}