


ALTER TABLE `log_action` ADD INDEX(`state`);
ALTER TABLE `log_action` ADD INDEX(`mission_id`);



ALTER TABLE `log_action` DROP INDEX `state`;
ALTER TABLE `log_action` DROP INDEX `mission_id`;
