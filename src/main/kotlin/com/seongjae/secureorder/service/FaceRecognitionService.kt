package com.seongjae.secureorder.service

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfRect
import org.opencv.core.Rect
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.objdetect.CascadeClassifier
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FaceRecognitionService {

    init {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    }

    // 얼굴 감지 및 비교 기능
    fun compareFaces(file1: MultipartFile, file2: MultipartFile): Boolean {
        val tempFile1 = File.createTempFile("face1-", file1.originalFilename)
        val tempFile2 = File.createTempFile("face2-", file2.originalFilename)

        file1.transferTo(tempFile1)
        file2.transferTo(tempFile2)

        val image1: Mat = Imgcodecs.imread(tempFile1.absolutePath)
        val image2: Mat = Imgcodecs.imread(tempFile2.absolutePath)

        if (image1.empty() || image2.empty()) {
            return false
        }

        val faceCascade = CascadeClassifier("src/main/resources/haarcascade_frontalface_alt.xml")
        val faceDetections1 = MatOfRect()
        val faceDetections2 = MatOfRect()

        faceCascade.detectMultiScale(image1, faceDetections1)
        faceCascade.detectMultiScale(image2, faceDetections2)

        // 두 이미지의 얼굴 수를 비교 (추후 유사도 비교 가능)
        return faceDetections1.toArray().size == faceDetections2.toArray().size
    }
}
