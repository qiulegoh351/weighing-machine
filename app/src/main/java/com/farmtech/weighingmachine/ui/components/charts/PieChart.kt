package com.farmtech.weighingmachine.ui.components.charts

import android.graphics.Color
import android.graphics.Typeface
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.farmtech.weighingmachine.ui.components.utils.hexToColor
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart as MKPieChart
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

@Composable
fun PieChart(
    modifier: Modifier = Modifier
) {
    val pieColors = listOf(
        hexToColor("#015D2C"),
        hexToColor("#F3811D"),
        hexToColor("#F4B135"),
        hexToColor("#2B4162"),
    )
    val xData = listOf(0.7f, 0.1f, 0.15f, 0.05f)
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val chart = MKPieChart(context)  // Initialise the chart
//            val entries: List<PieEntry> = xData.zip(yData) { x, y -> PieEntry(x, y) }  // Convert the x and y data into entries
            // Create empty labels by passing empty strings
            // Set padding inside the chart
            chart.setExtraOffsets(0f, 0f, 0f, 0f)  // Adjust these values as needed (left, top, right, bottom)

            val entries: List<PieEntry> = xData.map { PieEntry(it, "") }
            val dataSet = PieDataSet(entries, "") // Create a dataset of entries
            dataSet.colors = pieColors.map { it.toArgb() }
            dataSet.setDrawValues(false)
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            chart.data = PieData(dataSet)  // Pass the dataset to the chart
            chart.dragDecelerationFrictionCoef = 0.75f
            chart.description.isEnabled = false
            chart.legend.isEnabled = false
            chart.holeRadius = 72f
            chart.isDrawHoleEnabled = true
            chart.transparentCircleRadius = 6f
            chart.animateY(500, Easing.EaseInOutCubic)
            chart.setHoleColor(Color.TRANSPARENT)
//            chart.setTouchEnabled(false)
            chart.isHighlightPerTapEnabled = false
            chart.isDragDecelerationEnabled = false
            chart.isRotationEnabled = false
            chart.centerText = "100%"
            chart.setCenterTextSize(30f)  // Set font size
            chart.setCenterTextTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))  // Set font weight to bold


            // Refresh and return the chart
            chart.invalidate()
            chart
        }
    )
}