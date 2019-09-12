-- ENTITES TO BE IMPLEMENTED
-- defaultclient secret: KsquareChangeThis
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 authorities, access_token_validity, refresh_token_validity, web_server_redirect_uri)
VALUES
('defaultclient', '$2a$10$V1fb2.rr7/7wnFueyT37N.86qqJS/LzqVURy5kKXq.d5KiAgvmylu', 'read,write,trust','authorization_code',
 'USER', 1800, 2592000, 'http://localhost:8080/dashboard') ON CONFLICT (client_id) DO NOTHING;