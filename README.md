# Google-Maps-Directions  

Uma biblioteca open-source para a [**Google Directions API**](https://developers.google.com/maps/documentation/directions/).

[![Build Status](https://travis-ci.org/ricardolonga/google-maps-directions.png?branch=master)](https://travis-ci.org/ricardolonga/google-maps-directions)

## Requisitos  

Um servidor de aplicações Java EE 6 em virtude da utilização de CDI (preferencialmente JBoss AS 7.x ou superior). 

## Como usar

```
@Inject
private DirectionsSearch directionsSearh;

public void example() {
    DirectionsResponse response = directionsSearch.from(FLORIANOPOLIS)
                                                  .waypoint(SAO_JOSE)
                            				      .to(PALHOCA)
                            				      .go();
}
```
