-- `jpa-playground`.`user` definition
-- 사용자 테이블
CREATE TABLE `user` (
    `uniqueKey` int NOT NULL AUTO_INCREMENT COMMENT '유니크키',
    `userId` varchar(100) DEFAULT NULL COMMENT '사용자 아이디',
    `userPassword` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 비밀번호',
    `userName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 이름',
    `userEmail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '사용자 이메일',
    `userPhone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '사용자 핸드폰번호',
    `userRole` enum('ADMIN','USER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER' COMMENT '사용자 권한',
    `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 가입날짜',
    `edit_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 수정날짜',
    PRIMARY KEY (`uniqueKey`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- `jpa-playground`.`board` definition
-- 게시판 테이블
CREATE TABLE board (
   id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 ID',
   title VARCHAR(255) NOT NULL COMMENT '제목',
   content TEXT NOT NULL COMMENT '내용',
   writer VARCHAR(100) NOT NULL COMMENT '작성자',
   created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일시',
   updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
   view_count INT DEFAULT 0 COMMENT '조회수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- `jpa-playground`.`product` definition
-- 상품 테이블
CREATE TABLE `product` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '상품 ID (PK)',
   `name` varchar(255) NOT NULL COMMENT '상품명',
   `description` text COMMENT '상품 설명',
   `price` int NOT NULL COMMENT '상품 가격(원)',
   `stock_quantity` int DEFAULT '0' COMMENT '재고 수량',
   `status` enum('AVAILABLE','SOLD_OUT','DISCONTINUED') DEFAULT 'AVAILABLE' COMMENT '상품 상태 (판매중, 품절, 단종)',
   `category` enum('ELECTRONICS','BOOKS','FOOD','ETC') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '상품 카테고리 (예: 전자제품, 도서, 식료품, 기타)',
   `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
   `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 테이블';