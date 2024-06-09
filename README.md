## 用途
Udemy　Kubernetes入門のCICD（GitHub　Actions使用）で使用するレポジトリ

## アクセス方法

http://localhost:8080
http://localhost:8080/hello

## Docker

cd cicd-web
docker build . -t web-cicd
docker image ls -a
docker run --rm -p 8000:8080 --name web-cicd web-cicd