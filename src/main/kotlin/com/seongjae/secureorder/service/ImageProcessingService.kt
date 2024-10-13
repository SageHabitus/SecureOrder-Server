package com.seongjae.secureorder.service

import org.opencv.core.Mat
import org.opencv.core.MatOfRect
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.objdetect.CascadeClassifier
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class ImageProcessingService {

    private val faceCascade = CascadeClassifier("haarcascade_frontalface_alt.xml") // 얼굴 인식 모델

    fun processImage(file: MultipartFile): String {
        val tempFile = File.createTempFile("uploaded-", file.originalFilename)
        file.transferTo(tempFile)

        // 이미지 로드
        val image: Mat = Imgcodecs.imread(tempFile.absolutePath.toString())

        if (image.empty()) {
            return "Error: Unable to load image."
        }

        // 얼굴 감지
        val faceDetections = MatOfRect()
        faceCascade.detectMultiScale(image, faceDetections)

        return "Detected ${faceDetections.toArray().size} faces"
    }
}