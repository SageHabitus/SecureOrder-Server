# SecureOrder-Server (TBD)

### 🛠 **프로젝트 개요**

SecureOrder는 사용자의 인증을 안전하게 처리하고, 메뉴 조회 및 주문까지 원활하게 관리하는 **레스토랑 보안 앱**입니다. 이 프로젝트는 안드로이드 애플리케이션과 백엔드 서버로 구성되어 있으며, 사용자 경험을 극대화하기 위해 OTP, eKYC(전자 신원 확인), 얼굴 인식, JWT 기반 인증을 포함한 다양한 보안 기능을 제공합니다.

---

## 🔄 **전체적인 사용자 흐름**

1. **로그인**: 사용자는 이메일과 비밀번호를 통해 로그인하거나 OTP 인증을 통해 계정을 확인합니다.
2. **ID 인증**: 사용자 신분증을 업로드하여 ID 인증을 완료합니다.
3. **얼굴 인식**: 사용자 얼굴을 인식하여 추가적인 보안 절차를 진행합니다.
4. **메뉴 조회**: 인증이 완료된 사용자는 레스토랑 메뉴를 탐색하고, 카테고리별로 메뉴를 확인할 수 있습니다.
5. **주문**: 사용자는 원하는 메뉴를 선택해 주문을 완료하고, 주문 내역은 서버와 동기화됩니다.

### **주요 기능**
- **JWT 인증 및 세션 관리**: 사용자 로그인 및 토큰 발급
- **OTP 인증**: 이메일 기반 OTP 발송 및 검증
- **eKYC 및 ID 인증**: 신분증 업로드 후 OCR을 통한 신원 확인
- **메뉴 관리 API**: 레스토랑의 메뉴 조회 및 주문 관리
- **주문 처리**: 사용자의 주문을 처리하고, 주문 내역은 DB에 저장

### **기술 스택**
- **언어**: Kotlin, Spring Boot
- **데이터베이스**: PostgreSQL
- **보안**: Spring Security (JWT 인증)
- **ID 인증**: Tesseract OCR (신분증 검증)
- **얼굴 인식**: OpenCV (얼굴 인식)
  
### **설치 및 실행 방법**
1. **프로젝트 클론**:
   ```bash
   git clone https://github.com/yourusername/SecureOrder-Backend.git
   ```
   
2. **PostgreSQL 데이터베이스 설정**:
   - PostgreSQL 설치 후 `application.properties` 파일에 DB 정보를 설정합니다.
   - 
3. **백엔드 서버 실행**:
   ```bash
   ./gradlew bootRun
   ```
   
4. **API 문서 확인**:
   Swagger를 통해 API 문서에 접근할 수 있습니다:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---
