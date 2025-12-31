
INSERT INTO links.links (id, token, url, created_at, expired_at)
VALUES
(1, '124alx', 'https://github.com/alxsshv/', '2024-12-20 12:00:00', '2024-12-20 12:00:00'),
(2, '123mvn', 'https://mvnrepository.com/', '2024-12-20 12:00:00', '2024-12-20 12:00:00');

SELECT setval('links_seq', 3);

