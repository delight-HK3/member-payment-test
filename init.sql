CREATE DATABASE IF NOT EXISTS point DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE point;

create user 'pointer'@'%' identified by 'pointer';

grant select, insert, update, delete on point.* to 'pointer'@'%';

-- `point`.`member` definition

CREATE TABLE `member` (
  `viewcount` int NOT NULL DEFAULT '0' COMMENT '회원 조회수',
  `created_at` datetime(6) NOT NULL COMMENT '생성 시간',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '회원 ID (고유 키)',
  `version` bigint DEFAULT '0',
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '회원 이름',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-26 16:17:36', 1, 0, '홍길동');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-26 16:17:38', 2, 0, '김길동');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-26 16:17:39', 3, 0, '김기명');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-26 16:17:41', 4, 0, '박종건');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-27 15:35:08', 5, 0, '안성수');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-27 15:35:10', 6, 0, '차서하');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-28 15:35:11', 7, 0, '성한수');

INSERT INTO `point`.`member`
(viewcount, created_at, id, version, name)
VALUES(0, '2025-04-28 15:35:12', 8, 0, '이화연');

-- `point`.`point` definition

CREATE TABLE `point` (
  `amountpoint` int DEFAULT NULL COMMENT '남은 포인트',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '결제 일련번호 ID (고유 키)',
  `member_id` bigint NOT NULL COMMENT '회원 ID (외래 키)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe89kvxel18suao0yk1h4bsc3` (`member_id`),
  CONSTRAINT `FKbet7cyy000fgj8pm7pbuuur46` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `point`.`point`
(amountpoint, id, member_id)
VALUES(0, 1, 1);

INSERT INTO `point`.`point`
(amountpoint, id, member_id)
VALUES(0, 2, 2);

INSERT INTO `point`.`point`
(amountpoint, id, member_id)
VALUES(0, 3, 3);

INSERT INTO `point`.`point`
(amountpoint, id, member_id)
VALUES(0, 4, 4);

-- `point`.payment_log definition

CREATE TABLE `payment_log` (
  `amount` int DEFAULT NULL COMMENT '결제 금액',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '결제 일련번호 ID (고유 키)',
  `member_id` bigint NOT NULL COMMENT '회원 ID (외래 키)',
  `code` int DEFAULT NULL COMMENT '결제 상태 코드',
  `created_at` datetime(6) NOT NULL COMMENT '생성 시간',
  `message` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '결제 상태 메세지',
  `payment` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '결제 플랫폼',
  PRIMARY KEY (`id`),
  KEY `FKgb3ynxholti329wv5jfpcoh1t` (`member_id`),
  CONSTRAINT `FKgb3ynxholti329wv5jfpcoh1t` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;