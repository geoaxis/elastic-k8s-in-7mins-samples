# elastic-k8s-in-7mins-samples
A sample application suite for elastic community conference talk `Elastic and kubernetes on your dev machine in 7 minutes`


##Pre-Req Software
- Docker for Desktop with kubernetes enabled (this  comes with kubectl and kustomize)
- Skaffold
- Helm
- The ability to run things as a privileged user ()

## Setup
- If you have already created a local-elastic-stack then skip this step. Otherwise install nginx ingress 
  ```
  helm upgrade --install ingress-nginx ingress-nginx \
  --repo https://kubernetes.github.io/ingress-nginx \
  --namespace ingress-nginx --create-namespace
  ```
- Install bitnami chart repo for postgres and redis `helm repo add bitnami https://charts.bitnami.com/bitnami`
- It is best to delete a previously create postgres persistent volume claim  `kubectl delete pvc data-eccpg-postgresql-0`
- Install a fresh postgres on k8s 
	```
	helm install eccpg bitnami/postgresql \
  		--set auth.username=eccpg_user \
  		--set auth.password=eccpg_password \
  		--set auth.database=eccpg
	```
- Install a redis cluster `helm install eccredis oci://registry-1.docker.io/bitnamicharts/redis`


helm install eccpg bitnami/postgresql \
  --set auth.username=eccpg_user \
  --set auth.password=eccpg_password \
  --set auth.database=eccpg



## Running the applications
- Go to the folders of eccfe, eccnodejs and eccjava in seperate terminals and run `skaffold dev`
