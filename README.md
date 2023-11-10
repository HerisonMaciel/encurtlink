# encurtlink

Para executar com docker faça os seguintes passos:
- Abra o cmd na pasta do projeto
- baixe a imagem do mongoDB com esse comando: docker pull mongo
- Gere o arquivo jar com o comando: nvm install (caso de erro gere na propria IDE)
- Va ate a pasta resources e execulte o comando: docker-compose up -d

ApiRest
- POST: http://localhost:9090/encurtador/genereteUrl
      {
        "original_url": "teste.com"
      }
- POST: http://localhost:9090/encurtador/genereteUrl
      {
        "original_url": "teste.com",
        "alias": "teste"
      }
- GET:  http://localhost:9090/encurtador/ConsultAlias?alias=teste
- GET:  http://localhost:9090/encurtador/consultTop10

Swegger
- http://localhost:9090/swagger-ui/index.html

Futuras melhorias
- Acrescentar um LoodBalance
- Acrescentar testes unitarios e de integração
