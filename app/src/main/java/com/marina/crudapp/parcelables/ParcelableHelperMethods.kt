package com.marina.crudapp.parcelables

import com.marina.domain.dto.user.User

object ParcelableHelperMethods {
    fun User.toParcelable() = UserParcelable(
        id = this.id,
        name = this.name,
        birthdate = this.birthdate
    )

    fun UserParcelable.toUndoParcelable() = User(
        id = this.id,
        name = this.name,
        birthdate = this.birthdate
    )
}