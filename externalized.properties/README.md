# Externalize application configuration

Once a docker image of an application build same image can be deployed to multiple environment 
But each environment has separate configuration, like database, JMS and so on....
to externalize the application configuration below are the method could be used
1) Spring Cloud Config
2) Kubernetes ConfigMap and Secret

Here I am going to describe the Kubernetes *ConfigMap* and *Secret* to externalize the application configuration.

ConfigMaps and Secrets can be configured in two ways:

1) As Environment Variables
2) As Files

# As Environment Variables 
Create kubernetes ConfigMap and Secrets, sample snippet is below
``` yaml
# ConfigMap

apiVersion: v1
kind: ConfigMap
metadata:
name: dev-extranalized-config
data:
organization.name: abc extranalized dev
organization.title: abc extranalized dev organization title
organization.contact: abc extranalized dev organization contact
``` 

``` yaml
# Secrets
apiVersion: v1
kind: Secret
metadata:
name: dev-extranalized-secret
type: Opaque
data:
# You can include additional key value pairs as you do with Opaque Secrets
database.username: devuser1
database.password: MWYyZDFlMmU2N2Rm
``` 

``` yaml
# Application deployment snippet to use above ConfigMap and Secret as Environment variable
env:
- name: SERVER_PORT
  value: "9090"
- name: SPRING_PROFILES_ACTIVE
  value: dev
envFrom:
- configMapRef:
    name: dev-extranalized-config
- secretRef:
    name: dev-extranalized-secret
```   
Once docker is build and deployed to kubernetes cluster hit URL to validate the response.

Url : http://{IP_ADDRESS}:{PORT}/properties/title
``` json
Response: 
    {
        "databaseUsername": "u�����",
        "databasePassword": "1f2d1e2e67df",
        "databaseService": "clear text default is base64 encoded strings.",
        "config": {
            "title": "abc extranalized dev organization title"
        }
    }
```

