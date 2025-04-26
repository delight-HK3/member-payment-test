CREATE DATABASE IF NOT EXISTS point DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE point;

create user 'pointer'@'%' identified by 'pointer';

grant select, insert, update, delete on point.* to 'pointer'@'%';

CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '회원 ID (고유 키)',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '회원 이름',
  `viewcount` int NOT NULL COMMENT '회원 조회수',
  `created_at` datetime(6) NOT NULL COMMENT '생성 시간',
  `version` bigint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `point`.`member`
(id, name, viewcount, created_at, version)
VALUES(1, '홍길동', 0, '2025-04-26 16:17:36', 0);

INSERT INTO `point`.`member`
(id, name, viewcount, created_at, version)
VALUES(2, '김길동', 0, '2025-04-26 16:17:38', 0);

INSERT INTO `point`.`member`
(id, name, viewcount, created_at, version)
VALUES(3, '김기명', 0, '2025-04-26 16:17:39', 0);

INSERT INTO `point`.`member`
(id, name, viewcount, created_at, version)
VALUES(4, '박종건', 0, '2025-04-26 16:17:41', 0);