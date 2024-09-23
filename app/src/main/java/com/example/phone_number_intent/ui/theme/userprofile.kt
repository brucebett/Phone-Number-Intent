package com.example.phone_number_intent.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidfactory.fakestore.R
import com.androidfactory.fakestore.model.domain.user.User
import javax.inject.Inject

class UserProfileItemGenerator @Inject constructor() {

    data class UserProfileUiItem(
        @DrawableRes val iconRes: Int,
        val headerText: String,
        val infoText: String
    )

    fun buildItems(user: User): List<UserProfileUiItem> {
        return buildList {
            add(
                UserProfileUiItem(
                    iconRes = R.drawable.ic_person_24,
                    headerText = "Username",
                    infoText = user.username
                )
            )
            add(
                UserProfileUiItem(
                    iconRes = R.drawable.ic_round_phone_24,
                    headerText = "Phone number",
                    infoText = user.phoneNumber
                )
            )
            add(
                UserProfileUiItem(
                    iconRes = R.drawable.ic_round_location_24,
                    headerText = "Location",
                    infoText = "${user.address.street}, ${user.address.city}, ${user.address.zipcode}"
                )
            )
        }
    }
}

@Composable
fun UserProfileItem(item: UserProfileItemGenerator.UserProfileUiItem) {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = item.iconRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = item.headerText)
            Text(text = item.infoText)
        }
    }
}

@Composable
fun UserProfileScreen(userProfileItems: List<UserProfileItemGenerator.UserProfileUiItem>) {
    Column {
        userProfileItems.forEach { item ->
            UserProfileItem(item = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    val user = User(
        username = "john_doe",
        phoneNumber = "123-456-7890",
        address = Address(
            street = "123 Main St",
            city = "New York",
            zipcode = "10001"
        )
    )

    val generator = UserProfileItemGenerator()
    val items = generator.buildItems(user)

    UserProfileScreen(userProfileItems = items)
}
