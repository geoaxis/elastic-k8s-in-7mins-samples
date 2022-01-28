##Pre-Reqs
- Docker for Desktop with kubernetes enabled (this  comes with kubectl and kustomize)
- Skaffold
- Helm
- Install postgres and redis from bitnami
  - `helm repo add bitnami https://charts.bitnami.com/bitnami`
  - `helm install eccpg bitnami/postgresql;"`
  - `helm install eccredis bitnami/redis`

  export POSTGRESQL_PASSWORD=$(kubectl get secret --namespace default eccpg-postgresql -o jsonpath="{.data.postgresql-password}" | base64 --decode)

