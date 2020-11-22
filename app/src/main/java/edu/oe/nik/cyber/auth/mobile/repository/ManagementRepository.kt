package edu.oe.nik.cyber.auth.mobile.repository

import edu.oe.nik.cyber.auth.mobile.network.management.ManagementApi
import javax.inject.Inject

class ManagementRepository @Inject constructor(
    val managementApi: ManagementApi
) {
}