# elastic-k8s-in-7mins-samples
A sample application suite for elastic community 2022 conference talk `Elastic and kubernetes on your dev machine in 7 minutes`


## Pre-Req Software
- Docker for Desktop with kubernetes enabled (this  comes with kubectl and kustomize)
- Skaffold
- Helm

You willl also need the ability to run software as a privileged user.

## Setup 
- If you have already created a local-elastic-stack then skip this step. Install nginx ingress using [helm quickstart](https://kubernetes.github.io/ingress-nginx/deploy/#quick-start).
```
kubectl create namespace ingress-nginx;
kubectl create secret tls nginx-tls-secret --key server.key --cert server.crt -n ingress-nginx;
helm upgrade --install ingress-nginx ingress-nginx --repo https://kubernetes.github.io/ingress-nginx --namespace ingress-nginx --create-namespace --set controller.config.compute-full-forwarded-for='"true"' --set controller.config.use-forwarded-headers='"true"' --set controller.extraArgs.default-ssl-certificate=ingress-nginx/nginx-tls-secret;

```
- It is best to delete a previously create postgres persistent volume claim  `kubectl delete pvc data-eccpg-postgresql-0`
- Install a fresh postgres on k8s 
	```
	helm install eccpg oci://registry-1.docker.io/bitnamicharts/postgresql  --set auth.username=eccpg_user  --set auth.password=eccpg_password  --set auth.database=eccpg
	```
- Install a redis cluster 
  ```
  helm install eccredis oci://registry-1.docker.io/bitnamicharts/redis
  ```


## Running the applications
- Go to the folders of eccfe, eccnodejs and eccjava in seperate terminals and run `skaffold dev`
- Browse to [eccfe](http://kubernetes.docker.internal/eccfe) and do some actions. Then see the results in [kibana](https://kubernetes.docker.internal/kibana)


## Todo
- Use some sort of migration scripts for the database
- Make sure logging for things like redis can be changed on the fly or at least easily upon new compilation
- Make sure that the application work without explicit deps where possible. i.e. the k8s should start if some thing is not available and try to connect again at later stage
- Update to latest version of elastic stack 