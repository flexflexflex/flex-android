package uz.sesh.flex.flex

import org.junit.Test
import uz.sesh.flex.flex.utils.Validation

class RegistrationActivityUnitTest{
    @Test
    fun phoneIsCorrect(){
        var phoneNumber = Validation.validatePhoneNumber("+++++++++++998935823825")
        println(phoneNumber)
        assert(!phoneNumber.contains("+"))

    }
}