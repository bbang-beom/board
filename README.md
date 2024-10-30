# 게시판 만들기
## 기본사항: CRUD
1. 생성(Create): 이름, 제목, 내용을 입력받아 게시물 생성, 게시글의 고유 index 생성하여 생성할 때 마다 자동으로 1증가
2. 읽기(Read): 게시물 목록 출력(전체 목록 출력, 검색어 받아 해당 검색어를 포함한 목록 출력 2가지)
3. 수정(Update): 제목, 내용 수정
4. 삭제(Delete): 게시물 삭제

## 기본사항 후 추가 기능
1. 글을 작성할 때 제목은 반드시 입력하고(Null 불가), 이름은 입력하지 않으면 "익명"으로 생성(Null 허용)
2. 게시물 목록 출력 시 게시글이 없으면 안내 메시지 출력 
3. index를 입력받아 해당 index의 글 내용 출력
4. 조회 수를 생성하여 index로 글을 조회할 때 마다 조회 수 1 증가
5. 추천 비추천을 만들어 해당 글을 조회 후 추천하면 +1 비추천하면 -1
6. 메뉴나 옵션을 입력받을 때 선택 범위를 넘어서거나 다른 타입으로 선택했을 시 안내 메시지 출력
7. 정렬하여 출력(조회 순, 추천 순)
8. 파일 save 및 load 기능
