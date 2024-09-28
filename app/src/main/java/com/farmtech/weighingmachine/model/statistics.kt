package com.farmtech.weighingmachine.model

data class StatisticStatus(
    val slug: String,
    val value: Number
)

data class Statistic(
    val crop_id: Int,
    val crop_name: String,
    val percentage: Number,
    val weight: Number,
    val color: String,
    val statistic: StatisticStatus?
)


data class Crop(
    val cropItemId: Int,
    val cropNo: String,
    val cropItemName: String,
    val collectDate: String,
    val weight: Number
)

val statistics = listOf(
    Statistic(
        crop_id = 1,
        crop_name = "Product Group 1",
        percentage = 10.9,
        weight = 3413.23,
        color = "#015D2C",
        statistic = StatisticStatus(slug = "increase", value = 13.4)
    ),
    Statistic(
        crop_id = 2,
        crop_name = "Product Group 2",
        percentage = 4.3,
        weight = 6581.49,
        color = "#F3811D",
        statistic = StatisticStatus(slug = "decrease", value = 13.2)
    ),
    Statistic(
        crop_id = 3,
        crop_name = "Musang King",
        percentage = 5.1,
        weight = 4774.14,
        color = "#F4B135",
        statistic = StatisticStatus(slug = "decrease", value = 13.4)
    ),
    Statistic(
        crop_id = 4,
        crop_name = "Product Group 4",
        percentage = 3.9,
        weight = 13819.33,
        color = "#2B4162",
        statistic = StatisticStatus(slug = "increase", value = 13.4)
    ),
    Statistic(
        crop_id = 5,
        crop_name = "Product Group 5",
        percentage = 10.9,
        weight = 3413.13,
        color = "#F4B135",
        statistic = null
    ),
    Statistic(
        crop_id = 6,
        crop_name = "Product Group 6",
        percentage = 8.9,
        weight = 1123.13,
        color = "#D41C3E",
        statistic = null
    ),
    Statistic(
        crop_id = 7,
        crop_name = "Product Group 7",
        percentage = 12.9,
        weight = 12213.13,
        color = "#FF5A5F",
        statistic = null
    ),
    Statistic(
        crop_id = 8,
        crop_name = "Product Group 8",
        percentage = 10.9,
        weight = 3413.13,
        color = "#F9B5AC",
        statistic = null
    ),
    Statistic(
        crop_id = 9,
        crop_name = "Product Group 9",
        percentage = 10.9,
        weight = 3413.13,
        color = "#2B4162",
        statistic = null
    ),
    Statistic(
        crop_id = 10,
        crop_name = "Product Group 10",
        percentage = 10.9,
        weight = 3413.13,
        color = "#00D3FF",
        statistic = null
    ),
    Statistic(
        crop_id = 11,
        crop_name = "Product Group 11",
        percentage = 10.9,
        weight = 3413.13,
        color = "#7EB09B",
        statistic = null
    ),
    Statistic(
        crop_id = 12,
        crop_name = "Product Group 12",
        percentage = 10.9,
        weight = 3413.13,
        color = "#ACE6BD",
        statistic = null
    ),
    Statistic(
        crop_id = 13,
        crop_name = "Product Group 13",
        percentage = 10.9,
        weight = 3413.13,
        color = "#F3811D",
        statistic = null
    )
)

val cropItems = arrayOf(
    Crop(
        cropItemId = 1,
        cropNo = "DK00031",
        cropItemName = "Product A",
        collectDate = "01-07-2024 3:51 PM",
        weight = 90.5
    ),
    Crop(
        cropItemId = 2,
        cropNo = "DK00032",
        cropItemName = "Product B",
        collectDate = "01-07-2024 4:51 PM",
        weight = 10.5
    ),
    Crop(
        cropItemId = 3,
        cropNo = "DK00033",
        cropItemName = "Product C",
        collectDate = "03-07-2024 3:51 PM",
        weight = 110.55
    ),
    Crop(
        cropItemId = 4,
        cropNo = "DK00034",
        cropItemName = "Product D",
        collectDate = "03-07-2024 1:51 AM",
        weight = 30.5
    ),
    Crop(
        cropItemId = 5,
        cropNo = "DK00035",
        cropItemName = "Product E",
        collectDate = "02-07-2024 3:00 AM",
        weight = 3.223
    ),
    Crop(
        cropItemId = 6,
        cropNo = "DK00036",
        cropItemName = "Product F",
        collectDate = "01-07-2024 8:51 PM",
        weight = 22290.66
    ),
    Crop(
        cropItemId = 7,
        cropNo = "DK00037",
        cropItemName = "Product G",
        collectDate = "01-07-2024 3:35 PM",
        weight = 120.11
    ),
    Crop(
        cropItemId = 8,
        cropNo = "DK00038",
        cropItemName = "Product H",
        collectDate = "01-07-2024 3:51 PM",
        weight = 99.2
    ),
    Crop(
        cropItemId = 9,
        cropNo = "DK00041",
        cropItemName = "Product I",
        collectDate = "01-07-2024 8:51 PM",
        weight = 910.33
    ),
    Crop(
        cropItemId = 10,
        cropNo = "DK00040",
        cropItemName = "Product J",
        collectDate = "01-07-2024 7:51 PM",
        weight = 290.45
    )
)

