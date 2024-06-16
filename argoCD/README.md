## 2. [ArgoCD](https://argo-cd.readthedocs.io/en/stable/)

### 2.1. Setup ArgoCD

1. Install ArgoCD (Version [v2.8.4](https://github.com/argoproj/argo-cd/releases/tag/v2.8.4))

    ```
    kubectl create namespace argocd
    kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/v2.8.4/manifests/install.yaml
    ```

1. Check
    ```
    kubectl get pod -n argocd
    NAME                                                READY   STATUS    RESTARTS   AGE
    argocd-application-controller-0                     1/1     Running   0          48s
    argocd-applicationset-controller-787bfd9669-xh6hh   1/1     Running   0          48s
    argocd-dex-server-bb76f899c-c92zj                   1/1     Running   0          48s
    argocd-notifications-controller-5557f7bb5b-mmpzv    1/1     Running   0          48s
    argocd-redis-b5d6bf5f5-zl6zm                        1/1     Running   0          48s
    argocd-repo-server-56998dcf9c-bz875                 1/1     Running   0          48s
    argocd-server-5985b6cf6f-44s47                      1/1     Running   0          48s
    ```

1. Expose port

    Either of the following methods:

    1. Create `Service` with `NodePort` type (port: 30080)

        ```
        kubectl apply -f argocd-server-node-port.yaml -n argocd
        ```

    1. Port forward the service (port: 30080)

        ```
        kubectl -n argocd port-forward service/argocd-server 30080:80
        ```

1. Login

    Open https://localhost:30080, click on `Advanced` and `Proceed to localhost (unsafe)` (this is ok because we're connecting to the argocd running in our local computer)

    - username: `admin`
    - password: `kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath='{.data.password}' | base64 --decode`

    <img src="docs/argocd.png" width="400"/>

### 2.2. Deploy an application using ArgoCD

1. Create `AppProject`
    ```
    kubectl apply -f argocd-appproject-test.yaml
    ```
1. Create `Application`

    ```
    kubectl apply -f argocd-application-sample-app.yaml
    ```

    <details>

    If you're updating manifests in https://github.com/nakamasato/fastapi-sample/tree/main/manifests/fastapi-sample

    ```yaml
     spec:
       project: test
       source:
    -    repoURL: https://github.com/nakamasato/kubernetes-basics.git # Forkしている場合はnakamasatoを自分のGithubアカウントに変更してく
    ださい
    -    targetRevision: v2.1.1
    -    path: 09-cicd/sample-app-manifests
    +    repoURL: https://github.com/nakamasato/fastapi-sample.git # Forkしている場合はnakamasatoを自分のGithubアカウントに変更してください
    +    path: manifests/fastapi-sample
    ```

    </details>
