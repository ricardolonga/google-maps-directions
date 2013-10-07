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
    Authenticator authenticator = new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication("your_proxy_user", "your_proxy_pass".toCharArray()));
        }
    };
    Authenticator.setDefault(authenticator);

    System.setProperty("http.proxyHost", "your_proxy_host");
    System.setProperty("http.proxyPort", "your_proxy_port");

    DirectionsResponse response = directionsSearch.create()
                                                  .from(FLORIANOPOLIS)
                                                  .waypoint(SAO_JOSE)
                                                  .to(PALHOCA)
                                                  .go();
}
```

## Autor

**Nome**: Ricardo Longa  
**Twitter**: @ricardolonga  
**E-mail**: [ricardo.longa@gmail.com](mailto://ricardo.longa@gmail.com)  
**Site**: [www.ricardolonga.com.br](http://www.ricardolonga.com.br "Site")  
