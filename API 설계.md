# API 설계

## 1. API 엔드포인트 정의
1. **사용자 목록 조회**: GET /users
2. **사용자 조회**: GET /users/{id}
3. **사용자 생성**: POST /users
4. **사용자 수정**: PUT /users/{id}
5. **사용자 삭제**: DELETE /users/{id}
6. **사용자 동기화 (웹훅 엔드포인트)**: POST /webhook

## 2. 요청 및 응답 형식 정의

### 1. 사용자 목록 조회 (GET /users)
- **요청**: 없음
- **응답 (JSON)**:
  ```json
  [
    {
      "id": 1,
      "name": "John Doe",
      "employeeId": "EMP001",
      "email": "john.doe@example.com",
      "department": "Engineering",
      "position": "Software Engineer",
      "note": "Senior developer"
    },
    ...
  ]

### 2. 사용자 조회 (GET /users/{id})
- **요청**: 없음
- **응답 (JSON)**:
  ```json
  [
    {
      "id": 1,
      "name": "John Doe",
      "employeeId": "EMP001",
      "email": "john.doe@example.com",
      "department": "Engineering",
      "position": "Software Engineer",
      "note": "Senior developer"
    },
    ...
  ]

 ### 3. 사용자 생성 (POST /users)
- **요청 (JSON)**:
 ```json
  [
    {
        "name": "John Doe",
        "employeeId": "EMP001",
        "email": "john.doe@example.com",
        "department": "Engineering",
        "position": "Software Engineer",
        "note": "Senior developer"
    }
  ] 
  ```
- **응답 (JSON)**:
  ```json
  [
    {
        "id": 1,
        "name": "John Doe",
        "employeeId": "EMP001",
        "email": "john.doe@example.com",
        "department": "Engineering",
        "position": "Software Engineer",
        "note": "Senior developer"
    }
  ] 
  ```

### 4. 사용자 수정 (PUT /users/{id})
- **요청 (JSON)**:
 ```json
  [
    {
        "name": "John Doe",
        "employeeId": "EMP001",
        "email": "john.doe@example.com",
        "department": "Engineering",
        "position": "Software Engineer",
        "note": "Senior developer"
    }
  ] 
  ```
- **응답 (JSON)**:
  ```json
  [
    {
        "id": 1,
        "name": "John Doe",
        "employeeId": "EMP001",
        "email": "john.doe@example.com",
        "department": "Engineering",
        "position": "Software Engineer",
        "note": "Senior developer"
    }
  ] 
  ```

### 5. 사용자 삭제 (DELETE /users/{id})
- **요청**: 없음
- **응답**: 없음 (204 No Content)

### 6. 사용자 동기화(웹훅 엔드포인트) (POST /webhook)
- **요청 (JSON)**:
 ```json
  [
    {
        "event": "USER_UPDATE",
        "user": {
            "id": 1,
            "name": "John Doe",
            "employeeId": "EMP001",
            "email": "john.doe@example.com",
            "department": "Engineering",
            "position": "Software Engineer",
            "note": "Senior developer"
        }
    }
  ] 
  ```
- **응답 (JSON)**:
  ```json
  [
    {
        "status": "success"
    }
  ] 
  ```