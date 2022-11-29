package com.test.cdcn_appmobile.ui.main.statistical

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View
import com.test.cdcn_appmobile.R
import com.test.cdcn_appmobile.extension.getHeight
import com.test.cdcn_appmobile.extension.getWidth

/*
 * Created by tuyen.dang on 10/15/2022
 */

class DrawView(context: Context) : View(context) {

//    private val initX = 200f
//    private val initY = context.getHeight() - 150f
//
//    private var textTitlePaint: Paint = Paint()
//    private var textValueOXYPaint: Paint = Paint()
//    private var textValueOXPaint: Paint = Paint()
//    private var redPaintBrushFill: Paint = Paint()
//    private var bluePaintBrushFill: Paint = Paint()
//    private var greenPaintBrushFill: Paint = Paint()
//    private var grayPaintBrushStroke: Paint = Paint()
//    private var graphPath = Path()
//    private var xDraw = initX
//    private var yDraw = initY
//    private var widthDrawMax = context.getWidth() * 4 / 5
//    private var heightDrawMax = context.getHeight() * 1 / 5
//    private var listPointUnitLine = ArrayList<Float>()
//    private var listPointDraw = ArrayList<Float>()
//    private var rangeOfOne = 0f
//
//
//    companion object {
//        private const val title = "Char Title"
//        private const val speedStep = 5f
//        private const val coefficient = 1000
//        private const val maxRangeOY = 450
//        private const val stepRange = maxRangeOY / 9
//        private val listFeeInYear: List<FeeInYear> = listOf(
//            FeeInYear(2010, 350, 300, 50),
//            FeeInYear(2011, 400, 352, 45),
//            FeeInYear(2012, 425, 347, 75),
//            FeeInYear(2013, 375, 295, 90),
//            FeeInYear(2014, 485, 300, 65),
//            FeeInYear(2015, 325, 305, 25),
//        )
//    }
//
//    init {
//        rangeOfOne = (widthDrawMax - initX) / listFeeInYear.size
//
//        textTitlePaint.apply {
//            color = Color.BLACK
//            textSize = 70f
//        }
//
//        textValueOXYPaint.apply {
//            color = Color.BLACK
//            textSize = 30f
//            textAlign = Paint.Align.RIGHT
//            initGraphPath()
//        }
//
//        textValueOXPaint.run {
//            color = Color.BLACK
//            textSize = 30f
//            textAlign = Paint.Align.CENTER
//        }
//
//        redPaintBrushFill.apply {
//            color = context.getColor(R.color.colorVenetianRed)
//            style = Paint.Style.FILL
//        }
//
//        bluePaintBrushFill.apply {
//            color = context.getColor(R.color.colorCyanAzure)
//            style = Paint.Style.FILL
//        }
//
//        greenPaintBrushFill.apply {
//            color = context.getColor(R.color.colorMiddleGreenYellow)
//            style = Paint.Style.FILL
//        }
//
//        grayPaintBrushStroke.apply {
//            color = context.getColor(R.color.colorChineseSilver)
//            style = Paint.Style.STROKE
//            strokeWidth = 5f
//            textSize = 30f
//        }
//
//        for (i in (0..9)) {
//            listPointUnitLine.add(
//                (initY - heightDrawMax) / maxRangeOY * stepRange * i + heightDrawMax
//            )
//        }
//        listPointUnitLine.reverse()
//
//        for (i in (listFeeInYear.indices)) {
//            listPointDraw.add(
//                (widthDrawMax - initX) / listFeeInYear.size * i
//            )
//        }
//
//    }
//
//    @SuppressLint("DrawAllocation")
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//        initGraphPath()
//
//        canvas?.run {
//            drawPath(graphPath, grayPaintBrushStroke)
//
//            drawText(title, widthDrawMax / 2, heightDrawMax / 2, textTitlePaint)
//
//            listPointUnitLine.forEachIndexed { index, element ->
//                drawPath(pathUnitLine(element), grayPaintBrushStroke)
//                if (yDraw < element) {
//                    drawText(
//                        (stepRange * index * coefficient).toString(),
//                        180f,
//                        element + 10f,
//                        textValueOXYPaint
//                    )
//                }
//            }
//
//            listPointDraw.forEachIndexed { index, element ->
//                val rectTurnover = rectValue(element.toInt(), listFeeInYear[index].turnover, 0)
//                val rectCost = rectValue(element.toInt(), listFeeInYear[index].cost, 1)
//                val rectProfit = rectValue(element.toInt(), listFeeInYear[index].profit, 2)
//
//                drawRect(rectTurnover, bluePaintBrushFill)
//                drawRect(rectCost, redPaintBrushFill)
//                drawRect(rectProfit, greenPaintBrushFill)
//
//                if (xDraw > element + (rangeOfOne / 8) + (rangeOfOne / 4) * 1 * 3 / 2 + initX) {
//                    drawText(
//                        listFeeInYear[index].year.toString(),
//                        element + (rangeOfOne / 8) + (rangeOfOne / 4) * 1 * 3 / 2 + initX,
//                        initY + 50f,
//                        textValueOXPaint.apply {
//                            textAlign = Paint.Align.CENTER
//                        }
//                    )
//                }
//            }
//
//            drawRectNote(0, 60, "Doanh thu", canvas, bluePaintBrushFill)
//            drawRectNote(1, 50, "Chi phí", canvas, redPaintBrushFill)
//            drawRectNote(2, 50, "Lợi nhuận trước thuế", canvas, greenPaintBrushFill)
//
//            if (xDraw < widthDrawMax)
//                xDraw += speedStep
//            if (yDraw > heightDrawMax)
//                yDraw -= speedStep
//
//            invalidate()
//        }
//
//    }
//
//    private fun initGraphPath() {
//        graphPath.moveTo(initX, initY)
//        graphPath.lineTo(initX, yDraw)
//    }
//
//    private fun pathUnitLine(startY: Float): Path {
//        val path = Path()
//        path.moveTo(initX, startY)
//        path.lineTo(xDraw, startY)
//        return path
//    }
//
//    private fun drawRectNote(
//        index: Int,
//        noteLength: Int,
//        title: String,
//        canvas: Canvas?,
//        paint: Paint,
//    ) {
//        val rect = Rect()
//        rect.set(
//            widthDrawMax.toInt() + 20,
//            initY.toInt() / 2 + (noteLength + 50) * index,
//            widthDrawMax.toInt() + 20 + noteLength,
//            initY.toInt() / 2 + 100 * index + noteLength,
//        )
//        canvas?.drawRect(rect, paint)
//        canvas?.drawText(
//            title,
//            widthDrawMax + 20f + noteLength + 20f,
//            initY / 2 + (noteLength + 50) * index + noteLength * 1 / 2 + 10f,
//            textValueOXPaint.apply {
//                textAlign = Paint.Align.LEFT
//            }
//        )
//    }
//
//    private fun rectValue(startX: Int, value: Int, index: Int): Rect {
//        val rect = Rect()
//        rect.set(
//            startX + (rangeOfOne / 8).toInt() + (rangeOfOne / 4).toInt() * index + initX.toInt(),
//            if (yDraw > initY.toInt() - fromValueToOY(value).toInt()) {
//                yDraw.toInt()
//            } else {
//                initY.toInt() - fromValueToOY(value).toInt()
//            },
//            startX + (rangeOfOne / 8).toInt() + (rangeOfOne / 4).toInt() * (index + 1) + initX.toInt(),
//            initY.toInt()
//        )
//        return rect
//    }
//
//    private fun fromValueToOY(value: Int): Float = (initY - heightDrawMax) / maxRangeOY * value

}
