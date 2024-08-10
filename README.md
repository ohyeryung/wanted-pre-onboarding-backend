
wanted-pre-onboarding-backend
---
프리온보딩 백엔드 인턴십 선발과제

## 과제 설명
### 서비스 개요

- 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

### 요구사항
- [x] 채용공고를 등록합니다.
- [x] 채용공고를 수정합니다. (회사 ID 이외 모두 수정 가능)
- [x] 채용공고를 삭제합니다. (DB에서 삭제)
- [x] 채용공고 목록을 가져옵니다.
- [x] 채용공고 검색 기능 구현. (선택사항 및 가산점요소)
- [x] 채용 상세 페이지를 가져옵니다. (선택사항 및 가산점요소)
  - “채용내용”이 추가적으로 담겨있음.
  - 해당 회사가 올린 다른 채용공고가 추가적으로 포함됩니다.
- [x] 사용자는 채용공고에 지원합니다. (선택사항 및 가산점요소)
  - 사용자는 1회만 지원 가능합니다.

### 개발 시 참조사항

- 위 예시 데이터는 구현의 편의를 위해 제공되는 정보이며, 요구사항(의도)을 만족시킨다면 다른 형태의 요청 및 리스폰스를 사용하여도 좋습니다.

- 필요한 모델: 회사, 사용자, 채용공고, 지원내역(선택사항)
  (이외 추가 모델정의는 자유입니다.)

- 위 제공된 필드명은 예시이며, 임의로 생성 가능합니다.

- 회사, 사용자 등록 절차는 생략합니다.
  (DB에 임의로 생성하여 진행)

- 로그인 등 사용자 인증절차(토큰 등)는 생략합니다.

- Frontend 요소(html, css, js 등)는 개발 범위에 제외됩니다.
  (구현시 불이익은 없지만, 평가에 이점 또한 없습니다.)

- 명시되지 않은 조건또한 자유롭게 개발 가능합니다.

### 필수 기술요건

- ORM 사용하여 구현
- RDBMS 사용 (SQLite, PostgreSQL,MySql 등)

---

## 프로젝트

### 개발 환경

- Java 11
- Gradle 8.8
- SpringBoot 2.7.4

### ERD
![Wanted_pre_onboarding_ERD](https://github.com/user-attachments/assets/17882f89-a526-4f5b-bfc4-31b017614027)


### API 명세
[API 명세서](https://documenter.getpostman.com/view/20456478/2sA3s3Fqkc)

### 기술 스택
<p>
<img src= "https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white">
<img src= "https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src= "https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">
<img src= "https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=spring security&logoColor=white">
<img src= "https://img.shields.io/badge/Springjpa-4FC08D?style=for-the-badge&logo=jpa&logoColor=white">
<img src= "https://img.shields.io/badge/QueryDSL-4695EB?style=for-the-badge&logo=&logoColor=white">
<img src= "https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<br>
<img src= "https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src= "https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white">
</p>

## 느낀 점

> ### 구현 방법 및 선정 이유
1. 기업과 회원의 ID 값을 UUID로 설정
 - 데이터의 고유성을 보장하기 위해 선택
 - 기업의 경우에는 추후 기업 코드를 생성해 분류하는 것도 좋겠다는 생각이 들었음
   
 2. 채용 공고 상세 조회 시 해당 기업의 공고 글 List 함께 반환
 - 상세 조회 중인 채용 공고를 제외한 나머지 공고 글들의 List가 반환됨
 - 조회 되는 채용 공고글이 중복되지 않게 하기 위함
   
 3. 검색 기능 구현 시 querydsl 활용
 - 조건을 검에 있어 유연하게 수정이 가능하기에 선택
 - 컴파일 시점에 오류를 검출할 수 있기에 코드의 안정성 측면에서 효율적
   
