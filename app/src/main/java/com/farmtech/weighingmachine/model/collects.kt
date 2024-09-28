package com.farmtech.weighingmachine.model

data class CropItem(
    val cropItemName: String,
    val cropItemId: Int
)

data class CropGroup(
    val cropId: Int,
    val cropName: String,
    val cropItems: List<CropItem>
)

val collects = listOf(
    CropGroup(
        cropId = 1,
        cropName = "Product Group",
        cropItems = listOf(
            CropItem(cropItemName = "Musang King", cropItemId = 1),
            CropItem(cropItemName = "Mao King", cropItemId = 2),
            CropItem(cropItemName = "D24", cropItemId = 3),
            CropItem(cropItemName = "Black Thorn", cropItemId = 4),
            CropItem(cropItemName = "Butter", cropItemId = 5),
            CropItem(cropItemName = "Red Prawn", cropItemId = 6),
            CropItem(cropItemName = "Sultan", cropItemId = 7),
            CropItem(cropItemName = "X.O", cropItemId = 8),
            CropItem(cropItemName = "D101", cropItemId = 9),
            CropItem(cropItemName = "D102", cropItemId = 10),
            CropItem(cropItemName = "D103", cropItemId = 11),
            CropItem(cropItemName = "D104", cropItemId = 12),
            CropItem(cropItemName = "D105", cropItemId = 13),
            CropItem(cropItemName = "D106", cropItemId = 14),
        )
    ),
    CropGroup(
        cropId = 2,
        cropName = "Group of Control D",
        cropItems = listOf(
            CropItem(cropItemName = "D1", cropItemId = 15),
            CropItem(cropItemName = "D2", cropItemId = 16),
            CropItem(cropItemName = "D3", cropItemId = 17),
            CropItem(cropItemName = "D4", cropItemId = 18),
            CropItem(cropItemName = "D5", cropItemId = 19),
            CropItem(cropItemName = "D6", cropItemId = 20),
            CropItem(cropItemName = "D7", cropItemId = 21),
            CropItem(cropItemName = "D8", cropItemId = 22),
            CropItem(cropItemName = "D9", cropItemId = 23),
            CropItem(cropItemName = "D10", cropItemId = 24),
            CropItem(cropItemName = "D11", cropItemId = 25),
            CropItem(cropItemName = "D12", cropItemId = 26),
        )
    ),
    CropGroup(
        cropId = 3,
        cropName = "Group of Control X",
        cropItems = listOf(
            CropItem(cropItemName = "X0", cropItemId = 27),
            CropItem(cropItemName = "X1", cropItemId = 28),
            CropItem(cropItemName = "X2", cropItemId = 29),
            CropItem(cropItemName = "X3", cropItemId = 30),
            CropItem(cropItemName = "X4", cropItemId = 31),
            CropItem(cropItemName = "X5", cropItemId = 32),
            CropItem(cropItemName = "X6", cropItemId = 33),
            CropItem(cropItemName = "X7", cropItemId = 34),
            CropItem(cropItemName = "X8", cropItemId = 35),
            CropItem(cropItemName = "X9", cropItemId = 36),
            CropItem(cropItemName = "X10", cropItemId = 37),
            CropItem(cropItemName = "X11", cropItemId = 38),
        )
    ),
    CropGroup(
        cropId = 4,
        cropName = "Group of Control A",
        cropItems = listOf(
            CropItem(cropItemName = "A0", cropItemId = 39),
            CropItem(cropItemName = "A1", cropItemId = 40),
            CropItem(cropItemName = "A2", cropItemId = 41),
            CropItem(cropItemName = "A3", cropItemId = 42),
            CropItem(cropItemName = "A4", cropItemId = 43),
            CropItem(cropItemName = "A5", cropItemId = 44),
            CropItem(cropItemName = "A6", cropItemId = 45),
            CropItem(cropItemName = "A7", cropItemId = 46),
            CropItem(cropItemName = "A8", cropItemId = 47),
            CropItem(cropItemName = "A9", cropItemId = 48),
            CropItem(cropItemName = "A10", cropItemId = 49),
            CropItem(cropItemName = "A11", cropItemId = 50),
        )
    )
)
