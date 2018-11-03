#### Istio and Demo Application Installation
```bash
kubectl apply -f install/kubernetes/helm/istio/templates/crds.yaml
kubectl apply -f install/kubernetes/istio-demo.yaml
kubectl create clusterrolebinding cluster-admin-binding     --clusterrole=cluster-admin     --user=$(gcloud config get-value core/account)
kubectl apply -f install/kubernetes/istio-demo.yaml
kubectl get pods -n istio-system
kubectl get pods -n istio-system -w
kubectl get pods -n istio-system
kubectl delete pod -n istio-system istio-grafana-post-install-4hfpl istio-pilot-66897c47dd-2qvxr   istio-telemetry-59fc577dc9-7l5xr 
kubectl get pods -n istio-system -w
kubectl get pods -n istio-system
kubectl get pods -n istio-system
kubectl create ns istio-app
kubectl label namespace istio-app istio-injection=enabled
kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml -n istio-app
kubectl get pods -n istio-app
kubectl get pods -n istio-app -w
kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml -n istio-app
kubectl get pods -n istio-system
kubectl get ingress -n istio
kubectl get ingress -n istio-system
kubectl get svc -n istio-system
kubectl get pods -n istio-app
kubectl get pods -n istio-app -o yaml | grep Port
kubectl port-forward -n istio-app productpage-v1-f8c8fb8-2gc5k 9080:9080
kubectl get svc -n istio-system
kubectl get pods -n istio-system
kubectl port-forward -n istio-system istio-tracing-ff94688bb-zvsx2 9411:9411
kubectl port-forward -n istio-system istio-tracing-ff94688bb-zvsx2 9n15011:15011
kubectl port-forward -n istio-system istio-tracing-ff94688bb-zvsx2 16686:16686
```

#### Start requests
```bash
export INGRESS_IP=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}')

while True; do curl -XGET -s http://$INGRESS_IP/productpage > /dev/null ; done 
```

##### Service Graph
```bash
kubectl -n istio-system port-forward \
  $(kubectl -n istio-system get pod -l app=servicegraph -o jsonpath='{.items[0].metadata.name}') \
  8088:8088
```
#### Grafana
```bash
kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana \
  -o jsonpath='{.items[0].metadata.name}') 3000:3000
```
