# Documentation administration système

Pour les besoins du projet, une base de données PostgresSQL a été mise en place sur un serveur privé virtuel.

## Serveur

| choix technologiques   |                         |
| ---------------------- | ----------------------- |
| fournisseur            | ovh france              |
| système d'exploitation | ubuntu server 18.04 LTS |
| paquets installés      | docker docker-compose   |

### Installation

#### Reverse-proxy

Reverse proxy nginx de l'image docker linuxserver/letsencrypt équipé de:

* **fail2ban** (protection DDOS)
* **certbot letsencrypt** (génération et renouvellement automatique de certificats SSL)

##### docker-compose

#### Pgadmin

Pgadmin, outil d'administration de base de données PostgresSQL, dans un conteneur docker.

##### docker-compose

```yaml

```

#### Postgres

Postgres, serveur de bases de données.

##### docker-compose

```yaml

```



