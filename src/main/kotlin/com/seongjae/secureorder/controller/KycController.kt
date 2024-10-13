package com.seongjae.secureorder.controller

import com.seongjae.secureorder.service.FaceRecognitionService
import com.seongjae.secureorder.service.OcrService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/kyc")
class KycController(
    private val ocrService: OcrService
) {

    @PostMapping("/id-verification")
    fun verifyIdCard(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val extractedText = ocrService.extractTextFromImage(file)
        return ResponseEntity.ok("Extracted Text: $extractedText")
    }
}
