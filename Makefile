build:
	docker build -t modelagem-orm .

run:
	docker run -it --rm modelagem-orm

buildrun: build run