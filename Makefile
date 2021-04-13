up:
	docker-compose -f postgres-compose.yml up -d

down:
	docker-compose -f postgres-compose.yml down

git:
	bash git-cli.sh

pull:
	git pull origin master
	git pull
