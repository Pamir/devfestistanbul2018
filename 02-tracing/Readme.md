### Migrating Jeager / Zipkin compatible applications to Spring tracing
#### Local Development
```bash
mvn clean install
docker 
#just run
```
```bash
gcloud services enable cloudtrace.googleapis.com
```

```xml
                <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-gcp-starter-trace</artifactId>
		</dependency>
```

```bash
export PROJECT_ID=$(gcloud config list --format 'value(core.project)')
gcloud iam service-accounts create tracer
gcloud projects add-iam-policy-binding ${PROJECT_ID} --member serviceAccount:tracer@${PROJECT_ID}.iam.gserviceaccount.com  --role roles/editor
gcloud iam service-accounts keys create ~/tracer.json  --iam-account tracer@${PROJECT_ID}.iam.gserviceaccount.com
-Dspring.profiles.active=tracer -Dspring.cloud.gcp.credentials.location=file:/Users/pamir/tracer.json
```

