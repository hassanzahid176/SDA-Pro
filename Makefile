.PHONY: compile run clean smoke docker

compile:
	find src -name "*.java" > sources.txt
	javac -d out @sources.txt

run: compile
	java -cp out MainDemo

smoke: compile
	java -cp out MainDemo | tee demo-output.txt

clean:
	rm -rf out sources.txt

docker:
	docker compose up --build
