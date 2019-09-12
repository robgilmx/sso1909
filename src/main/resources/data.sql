-- ENTITES TO BE IMPLEMENTED
-- defaultclient secret: KsquareChangeThis
-- admin default password: KsquareChangeThisPassword
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 authorities, access_token_validity, refresh_token_validity, web_server_redirect_uri)
VALUES
('defaultclient', '$2a$10$V1fb2.rr7/7wnFueyT37N.86qqJS/LzqVURy5kKXq.d5KiAgvmylu', 'read,write,trust','authorization_code',
 'USER', 1800, 2592000, 'http://localhost:8080/dashboard') ON CONFLICT (client_id) DO NOTHING;

INSERT INTO users (id, username, "password", "enabled","locked")
VALUES ('1', 'admin', '$2a$10$v1FVssx3MF.QrGDhFzL6eu9NZn2KZrXE6FaYglAUINSDRrFO.bvsq', 'true', 'true')
ON CONFLICT (id) DO NOTHING;

INSERT INTO user_role (users_id, roles)
VALUES ('1', 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;