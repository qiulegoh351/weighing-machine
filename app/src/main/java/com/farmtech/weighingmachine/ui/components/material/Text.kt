package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme
import androidx.compose.material3.Text as MaterialText

data class ThemeConfig(
    val color: Color,
    val style: TextStyle
)

/**
 * ===========================
 * Text
 * ===========================
 */
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    annotatedString: Any? = null,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current,
    isDefaultTheme: Boolean = false
) {
    // ======================== VARIABLES
    val theme = if (isDefaultTheme) ThemeConfig(
        color,
        style = if (style === LocalTextStyle.current) TypographyTheme.titleMedium else style
    )
    else ThemeConfig(
        color = if (color == Color.Unspecified) ColorTheme.textPrimary else color,
        style = if (style === LocalTextStyle.current) TypographyTheme.titleMedium else style
    )

    // ========================= VIEW
    MaterialText(
        text =text,
        modifier,
        color = theme.color,
        fontSize,
        fontStyle,
        fontWeight,
        fontFamily,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        softWrap,
        maxLines,
        minLines,
        onTextLayout,
        style = theme.style
    )
}