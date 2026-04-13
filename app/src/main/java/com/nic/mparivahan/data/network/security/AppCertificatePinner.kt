package com.nic.mparivahan.data.network.security

import okhttp3.CertificatePinner
import javax.inject.Inject

class AppCertificatePinner @Inject constructor(
//    private val apiController: ApiController
) {

    fun getPinner(): CertificatePinner {
        //apiController.getDomain() // NPU() // APIController.a().NPU()
        val domain = "napix.gov.in"
        //apiController.getPin() // NPK()  //APIController.a().NPK()
        val pin = "sha256/1e951ec0e92f22841eb55cfcf22425a161cb7de2eeee1ad5cc42ac315d7ae04c"


        return CertificatePinner.Builder()
            .add(domain, pin)
            .build()
    }
}