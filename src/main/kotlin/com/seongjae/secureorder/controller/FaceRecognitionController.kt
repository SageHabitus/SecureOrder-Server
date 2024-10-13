package com.seongjae.secureorder.controller

import com.seongjae.secureorder.service.FaceRecognitionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/kyc")
class FaceRecognitionController(
    private val faceRecognitionService: FaceRecognitionService
) {

    @PostMapping("/face-verification")
    fun verifyFace(@RequestParam("face1") face1: MultipartFile, @RequestParam("face2") face2: MultipartFile): ResponseEntity<String> {
        val isMatch = faceRecognitionService.compareFaces(face1, face2)
        return if (isMatch) {
            ResponseEntity.ok("Faces match")
        } else {
            ResponseEntity.badRequest().body("Faces do not match")
        }
    }
}

