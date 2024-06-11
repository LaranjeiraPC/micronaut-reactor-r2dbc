# .SILENT:

export COMPOSE_PROJECT_NAME=overdose

create-database:
	echo "Preparando container Postgres, Flyway..."
	cd docker/flyway;
		docker-compose up -d

stop-database:
	echo "Parando container Postgres, Flyway..."
	docker stop postgres flyway

start-database:
	echo "Preparando container Postgres, Flyway..."
	docker start postgres

clean:
	docker rm -v postgres flyway