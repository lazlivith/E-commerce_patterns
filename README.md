# 🛒 Projet E-commerce Java – Architecture & Design Patterns

## 📌 Description générale

Ce projet est une application Java de type e-commerce conçue dans un objectif pédagogique pour illustrer :

* L’architecture en couches (domain, service, repository, ui)
* Les principes SOLID
* Plusieurs Design Patterns :

  * **Strategy** (calcul de prix)
  * **Factory** (création des moyens de paiement)
  * **Decorator** (options produit)
  * **Singleton** (logger)
* L’écriture de **tests unitaires JUnit**

L’application simule le traitement d’une commande avec :

* Un choix de stratégie de prix
* Un choix de moyen de paiement
* Un enregistrement de la commande
* Un journal d’exécution

---

## 🧱 Architecture du projet

```
src/
├── main/
│   └── java/
│       └── com.ecommerce/
│           ├── domain/        # Entités métier (Order, Product, etc.)
│           ├── service/       # Logique métier (OrderService)
│           ├── repository/    # Accès aux données (OrderRepository)
│           ├── ui/            # Interface console (ConsoleUI)
│           ├── Patterns/
│           │   ├── strategy/  # Strategy Pattern
│           │   ├── factory/   # Factory Pattern
│           │   ├── decorator/ # Decorator Pattern
│           │   └── singleton/ # Singleton Pattern
│           └── Main.java      # Point d’entrée
│
└── test/
    └── java/
        └── com.ecommerce/
            └── Patterns/
                ├── strategy/
                │   └── PricingStrategyTest.java
                ├── factory/
                │   └── PaymentFactoryTest.java
                └── decorator/
                    └── GiftWrapDecoratorTest.java
```

---

## 🎯 Fonctionnalités principales

* Création de produits
* Application d’une stratégie de prix
* Décoration des produits (ex: emballage cadeau)
* Choix du moyen de paiement
* Traitement de la commande
* Enregistrement dans un dépôt simulé
* Journalisation via un Singleton

---

## 🧠 Design Patterns utilisés

### 1. Strategy – Calcul du prix

Permet de changer dynamiquement la méthode de calcul du prix.

Implémentations :

* `NormalPricing`
* `DiscountPricing`

Interface :

* `PricingStrategy`

---

### 2. Factory – Création des paiements

Centralise la création des objets de paiement.

Implémentations :

* `PayPalPayment`
* `CreditCardPayment`

Classe :

* `PaymentFactory`

---

### 3. Decorator – Options produit

Ajoute dynamiquement des fonctionnalités à un produit.

Implémentations :

* `GiftWrapDecorator`

Classe abstraite :

* `ProductDecorator`

---

### 4. Singleton – Logger

Assure qu’une seule instance du logger existe.

Classe :

* `Logger`

---

## 🧪 Tests unitaires (JUnit 5)

Le projet contient des tests unitaires pour valider les patterns.

### Tests implémentés :

* `PricingStrategyTest`
  ✔ Vérifie que :

  * `NormalPricing` retourne le même prix
  * `DiscountPricing` applique -10 %

* `PaymentFactoryTest`
  ✔ Vérifie que :

  * Le bon type de paiement est instancié

* `GiftWrapDecoratorTest`
  ✔ Vérifie que :

  * Le prix augmente avec l’option cadeau

### Lancer les tests

Dans IntelliJ IDEA :

1. Clic droit sur un fichier `*Test.java`
2. Run

Ou via Maven / Gradle si configuré.

---

## ▶️ Lancer l’application

1. Ouvrir le projet dans IntelliJ IDEA
2. Exécuter la classe `Main`
3. Suivre les instructions dans la console

---

## 🧩 Exemple de sortie console

```
Nom du client : lazz

[LOG] Traitement de la commande
Paiement via PayPal : 7.92 €
Email envoyé : Commande validée. Total = 7.92 €
[LOG] Commande enregistrée
```

---

## 📚 Technologies utilisées

* Java 17+
* JUnit 5
* IntelliJ IDEA

---

## ✍️ Auteur

Ahmed Rachid Bangoura

---

## 📄 Licence

Projet à usage pédagogique uniquement.
