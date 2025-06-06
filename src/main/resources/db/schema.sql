-- `jpa-playground`.`user` definition

CREATE TABLE `user` (
    `uniqueKey` int NOT NULL AUTO_INCREMENT COMMENT '유니크키',
    `userId` varchar(100) DEFAULT NULL COMMENT '사용자 아이디',
    `userPassword` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 비밀번호',
    `userName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 이름',
    `userEmail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '사용자 이메일',
    `userPhone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '사용자 핸드폰번호',
    `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 가입날짜',
    `edit_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 수정날짜',
    PRIMARY KEY (`uniqueKey`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;