up: # Start containers.
	docker compose up -d

att: # Start containers and show logs at real time.
	docker compose up

down: # Stop and remove containers.
	docker compose down

pull: # Download images.
	docker compose pull

db: # Create database.
	docker compose exec db bash -c "mysql -u root -ppassword workshop < /sql/clear.sql"
	docker compose exec db bash -c "mysql -u root -ppassword workshop < /sql/insert.sql"

