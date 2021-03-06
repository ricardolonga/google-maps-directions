﻿# Google-Maps-Directions  

Uma biblioteca open-source para a [**Google Directions API**](https://developers.google.com/maps/documentation/directions/).

[![Build Status](https://travis-ci.org/ricardolonga/google-maps-directions.png?branch=master)](https://travis-ci.org/ricardolonga/google-maps-directions)
[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/ricardolonga/google-maps-directions/trend.png)](https://bitdeli.com/free "Bitdeli Badge")


## Requisitos  

Um servidor de aplicações Java EE 6 em virtude da utilização de CDI (preferencialmente JBoss AS 7.x ou superior). 

## Como usar

```
@Inject
private DirectionsSearch directionsSearh;

public void example() {
    DirectionsResponse response = directionsSearch.create()
                                                  .from(FLORIANOPOLIS)
                                                  .waypoint(SAO_JOSE)
                                			      .to(PALHOCA)
                            				      .go();
}
```

## Utilizando proxy

```
@Inject
private DirectionsSearch directionsSearh;

public void example() {
    DirectionsResponse response = directionsSearch.create()
                                                  .from(FLORIANOPOLIS)
                                                  .waypoint(SAO_JOSE)
                                                  .to(PALHOCA)
                                                  .withProxy("your_proxy_host", "your_proxy_port", "your_proxy_user", "your_proxy_pass")
                                                  .go();
}
```

## Definindo locale (default = pt-br)

```
@Inject
private DirectionsSearch directionsSearh;

public void example() {
    DirectionsResponse response = directionsSearch.create()
                                                  .from(FLORIANOPOLIS)
                            				      .to(PALHOCA)
                                                  .withLocale(Locale.CANADA_FRENCH) // "fr-CA"
                            				      .go();
}
```

## Com rotas alternativas

```
@Inject
private DirectionsSearch directionsSearh;

public void example() {
    DirectionsResponse response = directionsSearch.create()
                                                  .from(FLORIANOPOLIS)
                                			      .to(PALHOCA)
                                                  .withAlternatives()
                            				      .go();
}
```

## Consultando rotas como cliente Premier

```
@Inject
private DirectionsSearch directionsSearh;

public void example() {
    DirectionsResponse response = directionsSearch.create()
                                                  .from(FLORIANOPOLIS)
                                			      .to(PALHOCA)
                                                  .asPremierClient("your_client_id", "your_cryptographic_key")
                            				      .go();
}
```

## Autor

**Nome**: Ricardo Longa  
**Twitter**: @ricardolonga  
**E-mail**: [ricardo.longa@gmail.com](mailto://ricardo.longa@gmail.com)  
**Site**: [www.ricardolonga.com.br](http://www.ricardolonga.com.br "Site")  
