# Normes de programmation - Projet FoodWatcher

## Commentaires

Toutes les méthodes doivent avoir une javadoc (excepté pour les accesseurs, les constructeurs, les toString, les getInstance) voici un exemple :  

```java
/**
     * commentaire qui décrit ce que fait la fonction
     * @param a description du paramètre a
     * @param c description du paramètre b
     * @return description de la valeur retournée
     */
    public String actionnerMachin(int a, char b) {
        // implémentation de la méthode
        return "string";
    }
```

> Raccourci: /** + ENTER 

## Casse

| Type       | Casse      |
| ---------- | ---------- |
| Classe     | CamelCase  |
| xml        | snake_case |
| tables sql | snake_case |

