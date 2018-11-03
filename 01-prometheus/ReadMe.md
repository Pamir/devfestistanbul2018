Build and Test Image

```bash
cd app
mvn clean install 
java -jar target/spring-boot-prometheus-example-0.0.1-SNAPSHOT.jar
gcloud auth configure-docker
docker build . -t spring-boot-prometheus-example
docker tag spring-boot-prometheus-example gcr.io/inspired-bus-194216/springboot-prometheus:latest
docker push gcr.io/inspired-bus-194216/springboot-prometheus:latest
```

#### Install Helm
```bash
kubectl create clusterrolebinding user-admin-binding --clusterrole=cluster-admin --user=$(gcloud config get-value account)
kubectl create serviceaccount tiller --namespace kube-system
kubectl create clusterrolebinding tiller-admin-binding --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
helm init --service-account=tiller
helm update
```

#### Install Prometheus
```bash
cd ../promethues/
helm repo add coreos https://s3-eu-west-1.amazonaws.com/coreos-charts/stable/
helm install coreos/prometheus-operator --name prometheus-operator --namespace monitoring
helm install coreos/kube-prometheus --name kube-prometheus --namespace monitoring -f kube-prometheus.yaml
helm install coreos/exporter-kubelets --name exporter-kubelets --namespace monitoring -f exporter-kubelets.yaml
kubectl apply -f kube-prometheus-grafana.yaml
helm install coreos/grafana --name prometheus-grafana --namespace monitoring -f grafana.yaml
kubectl get pods -n monitoring -w
```
#### Deploy like a boss
```bash
cd ../onprem/
kubectl create ns devfest
kubectl apply -f spring-dpl.yaml	
kubectl apply -f spring-svc-monitor.yaml
##todo port-forward promethues
##todo port-forward grafana
```

#### Monitor like a boss
```bash
cd ../ongcp
kubectl delete ns devfest
kubectl apply -f spring-dpl.yaml
curl -sSO "https://storage.googleapis.com/stackdriver-prometheus-documentation/rbac-setup.yml"
kubectl apply -f rbac-setup.yml --as=admin --as-group=system:masters
curl -sSO "https://storage.googleapis.com/stackdriver-prometheus-documentation/prometheus-service.yml"
# change _stackdriver_project_id _kubernetes_cluster_name _kubernetes_location
kubectl apply -f prometheus-service.yml

```

References</br>
[1] - https://cloud.google.com/monitoring/kubernetes-engine/prometheus


