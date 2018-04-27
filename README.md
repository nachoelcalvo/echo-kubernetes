# Commands to run echo service in the cloud

## Create cluster
gcloud container --project "my-kubernetes-project-202310" clusters create "echo-kluster" --zone "europe-west2" --machine-type "n1-standard-1" --image-type "GCI" --disk-size "100" --scopes "https://www.googleapis.com/auth/compute","https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --num-nodes "2" --network "default" --enable-cloud-logging --enable-cloud-monitoring

## Build docker image
docker build -t echo-spring:v3 .

## Tag image using container registry and project 
docker tag echo-spring:v3 eu.gcr.io/my-kubernetes-project-202310/echo-spring:v3

## Push image to gcr
docker push -- eu.gcr.io/my-kubernetes-project-202310/echo-spring:v3

## Create Deployment on kubernetes
kubectl create -f Deployment.yaml

## Delete cluster
gcloud container clusters delete "echo-kluster"
