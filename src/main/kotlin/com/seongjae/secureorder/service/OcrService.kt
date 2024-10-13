package com.seongjae.secureorder.service

import net.sourceforge.tess4j.Tesseract
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class OcrService {

    private val tesseract = Tesseract()

    init {
        tesseract.setDatapath("tessdata/") // Tesseract 데이터 경로 설정
        tesseract.setLanguage("eng") // 언어 설정
    }

    fun extractTextFromImage(file: MultipartFile): String {
        val tempFile = File.createTempFile("uploaded-", file.originalFilename)
        file.transferTo(tempFile)

        return tesseract.doOCR(tempFile)
    }
}
