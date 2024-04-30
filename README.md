# un-splash

[asdasdasd.webm](https://github.com/wsb7788/un-splash/assets/41473557/69637f40-1505-4a1c-835c-af1040550840)

## 피드 화면 (feature-feed-screen)
- 검색창, 검색어 입력 시 검색결과에 대한 이미지를 Paging3 라이브러리를 통해 노출합니다.
- 검색 결과 데이터는 room 연동해 캐싱됩니다.
- 이미지 오른쪽 상단 좋아요 버튼 클릭 시, 서버에 좋아요를 남기는 api를 요청하고, 로컬에 저장되어 즐겨찾기 화면에 노출됩니다.

## 즐겨찾기 화면 (feature-feavorites-screen)
- 피드 화면에서 좋아요 클릭한 이미지들을 저장해 노출합니다.
- 즐겨찾기 화면에서 좋아요 해제한 이미지는 피드 화면에도 반영됩니다.

## 사용 기술

Android / Kotlin / Compose / Hilt / Navigation / Room / Paging3 / Coil / Ktor / Flow

## 프로젝트 구조

CleanArchitecure를 적용한 Multi Module 프로젝트입니다.
VersionCatalog를 이용해 모듈 별 적용하는 라이브러리들의 버전을 관리했습니다.

SingleActivity와 중첩된 구조의 Navigation을 사용했습니다.
앱이 가지는 화면이 많아지거나 구조가 복잡할 때, 연관된 화면들을 하나로 묶어 관리하기가 편해집니다. 

MVVM 구조에 UDF 방식을 사용하여 사용자에게 필요한 정보를 제공합니다.

## 모듈 설명

### :build-logic

여러개로 나뉘어있는 모듈에 대한 설정과 의존관계를 보다 쉽게 정의하기 위해 사용했습니다.

각 모듈의 gradle 파일이 1대 1로 매핑되어 있습니다.
프로젝트 빌드 시 해당 모듈을 참조하게 됩니다.

각각의 라이브러리들을 Project의 확장 함수 형태로 추가할 수 있게 구현했습니다.
내부에 존재하는 매핑된 gradle 파일에서 필요한 라이브러리와 모듈 간 의존관계를 설정했습니다.

### :app

앱의 진입점입니다.
모든 모듈에대한 의존성을 가지고 있습니다.
의존성 주입을 위한 hilt 라이브러리에서 관리할 객체의 인스턴스에 대한 정의를 진행합니다.

### :common:designsystem

feature 모듈에서 공통적으로 사용하는 UI와 관련된 것들이 정의되어 있습니다. 

### :core:navigation

feature 모듈간의 의존성을 제거하기 위한 모듈입니다.

### :data:local

로컬 데이터베이스에 접근해 데이터를 주고 받는 방법을 알고 있는 모듈입니다.

### :data:remote

네트워크에 접근해 데이터를 주고 받는 방법을 알고 있는 모듈입니다.

### :data:model

네트워크 요청을 할 때 데이터를 주고 받는 DTO의 역할을 가진 모듈입니다.

### :data:repository :domain:repository

data layer에 요청을 하게 되는 모듈입니다.
의존관계 역전 법칙을 이용하여 순환 관계를 끊어낼 수 있습니다.

### :domain:entity

사용자에게 데이터를 표시하기 위한 클래스가 있는 모듈입니다.

### :domain:usecase

비즈니스 로직을 담게 되는 모듈입니다.
현재는 복잡한 비즈니스 로직이 존재하지 않기 때문에 포워딩의 역할만 수행하게 됩니다.

### :feature:home

사용자에게 표시될 UI를 담고 있는 모듈입니다.
밀접한 관계를 맺고 있는 화면들을 묶어 하나의 feature로 표현합니다.
앱의 크기가 커졌을 때 Play Feature Delivery를 이용할 수 있습니다.
