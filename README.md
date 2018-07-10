# Commands to run echo service in a GCP Kubernetes cluster

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

# Commands to run echo service in Open Shift Platform

## Create buildconfig based on Dockerfile
oc new-build --binary=true --name=echo-build

## Start build
oc start-build echo-build -n echo-cloud --from-file=echo.jar

Optionally, a docker image could be pushed to openshift internal registry as follows

#### Push Image from local
docker login openshift repository

#### login on openshift docker registry
docker login -u developer -p $(oc whoami -t) $(minishift openshift registry)

#### Push Image to repository and create Image Streams on the project
docker push 172.30.1.1:5000/echo-cloud/echo

## Create deploymentconfig from image
oc create deploymentconfig echo --image=172.30.1.1:5000/echo-cloud/echo-build
 
### Optionally, you can create an app from image as follows
oc new-app echo --name=echo

## Expose dc
oc expose dc/echo --port=8080 --type=LoadBalancer

## Expose svc
oc expose svc/echo

## Change permissions on project B to pull image from project A
oc policy add-role-to-user system:image-puller system:serviceaccount:poc-qa:admin --namespace=echo-cloud

## Image tagging / Promotion
oc tag echo-cloud/echo:latest echo-cloud/echo:promotetoQA
  or
oc tag echo-cloud/echo:latest poc-qa/echo:promotetoQA

## From echo-QA
oc create deploymentconfig echo --image=172.30.1.1:5000/echo-cloud/echo:promotetoQA
  or
oc create deploymentconfig echo --image=172.30.1.1:5000/poc-qa/echo:promotetoQA