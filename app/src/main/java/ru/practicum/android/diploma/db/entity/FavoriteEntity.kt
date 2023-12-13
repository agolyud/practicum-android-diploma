package ru.practicum.android.diploma.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String?,
    val url: String?,
    // area
    //val areaId: String,
    val areaName: String,
    //val areaUrl: String,
    // employer
    val idEmployer: String?,
    val alternateUrlEmployer: String?,
    val blacklistedEmployer: Boolean?,
    val logoUrlsEmployerOriginal: String?,
    val nameEmployer: String?,
    val trustedEmployer: Boolean?,
    val urlEmployer: String?,
    // employment
    val idEmployment: String?,
    val nameEmployment: String?,
    // experience
    val idExperience: String?,
    val nameExperience: String?,
    // currency
    val currency: String?,
    val from: Int?,
    val gross: Boolean,
    val to: Int?,
    // skills
    val keySkills: String?,
    // contacts
    val phone: String?,
    val contactName: String?,
    val email: String?,
    val comment: String?
)
